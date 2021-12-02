package com.mertyazilim.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DBC {

	private static final String NULL = null;
	static Connection conn = null;
	public static CallableStatement cstmt = null;
	public static PreparedStatement pstmt = null;
	private static ResultSet dBresults = null;
	private static Statement stmt;

	public static String jdbcdriver = "oracle.jdbc.driver.OracleDriver";
	public static String esnVal;
	public static String simVal;
	public static String pinVal;
	public static String statu;
	public static String actItemId;
	public static String sESN;
	public static String strMinVal;
	public static String minVal;
	public static String strVal;


	public static String callESN(String valESNPartNumber, ExtentTest extentTest) {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			String getESN = "{ ? = call sa.Get_Test_Esn(?)}";
			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);
			cstmt = conn.prepareCall(getESN);
			cstmt.registerOutParameter(1, Types.VARCHAR);

			cstmt.setString(2, valESNPartNumber);
			cstmt.executeUpdate();
			esnVal = cstmt.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (dBresults != null)
				dBresults.close();
			if (cstmt != null)
				conn.close();
			if (conn != null)
				conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println(esnVal);
		extentTest.log(LogStatus.INFO, "ESN Number is : " + esnVal + "  --> Part Number is :" + valESNPartNumber);
		return esnVal;
	}

	public static String callSIM(String valSIMPartNumber, ExtentTest extentTest) {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			String getSIM = "{ ? = call get_test_sim(?)}";
			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);
			cstmt = conn.prepareCall(getSIM);
			cstmt.registerOutParameter(1, Types.VARCHAR);

			cstmt.setString(2, valSIMPartNumber);
			cstmt.executeUpdate();
			simVal = cstmt.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (dBresults != null)
				dBresults.close();
			if (cstmt != null)
				conn.close();
			if (conn != null)
				conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println(simVal);
		extentTest.log(LogStatus.INFO, "SIM Number is : " + simVal + "  --> Part Number is :" + valSIMPartNumber);
		return simVal;
	}

	public static String callPIN(String valPINPartNumber, ExtentTest extentTest) {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			String getPIN = "{ ? = call Get_Test_Pin(?)}";
			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);
			cstmt = conn.prepareCall(getPIN);
			cstmt.registerOutParameter(1, Types.VARCHAR);

			cstmt.setString(2, valPINPartNumber);
			cstmt.executeUpdate();
			pinVal = cstmt.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (dBresults != null)
				dBresults.close();
			if (cstmt != null)
				conn.close();
			if (conn != null)
				conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println(pinVal);
		extentTest.log(LogStatus.INFO, "PIN Number is : " + pinVal + "  --> Part Number is :" + valPINPartNumber);

		return pinVal;
	}

	public static String CheckActionItem(String esnVal) {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);

			stmt = conn.createStatement();

			String query = "SELECT action_item_id, order_type,rate_plan, status, status_message,msid, min, esn, esn_hex, iccid, carrier_id, sequence_num \r\n"
					+ "FROM ig_transaction\r\n" + "WHERE esn in(" + esnVal + ")\r\n"
					+ "AND creation_date >= TRUNC(SYSDATE-30)\r\n" + "ORDER BY creation_date";

			dBresults = stmt.executeQuery(query);
			while (dBresults.next()) {

				statu = dBresults.getString("status");
				actItemId = dBresults.getString("action_item_id");
			}

			dBresults.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return statu;
	}

	public static String CheckESNforStage(String esnVal) {
		String STATUS = null;

		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);

			stmt = conn.createStatement();

//			String query = "select * from X_SERVICE_ORDER_STAGE where ESN=";
			String query1 = "SELECT * FROM SA.X_RTR_TRANS_DETAIL where ESN='" + esnVal + "'";
			dBresults = stmt.executeQuery(query1);
			while (dBresults.next()) {

				STATUS = dBresults.getString("STATUS");
//				actItemId = dBresults.getString("action_item_id");
			}

			dBresults.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return STATUS;
	}

	public static String StagedESN(String esnVal) {
		String sESN = null;

		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);
			stmt = conn.createStatement();
//			String query= "select * from X_SERVICE_ORDER_STAGE where ESN=100000011705976";
//			String query = "select * from X_SERVICE_ORDER_STAGE where ESN=";
			String query = "select * from X_SERVICE_ORDER_STAGE where ESN='" + esnVal + "'";
			dBresults = stmt.executeQuery(query);
			while (dBresults.next()) {

				sESN = dBresults.getString("ESN");
				System.out.println(sESN);
			}

			dBresults.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sESN;
	}

	public static void updateStatusW(String minNo) throws Exception {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);

			String queryUpdateStatusMsid = "update ig_transaction set status = 'W' , new_msid_flag = 'Y' where action_item_id IN (select action_item_id from ig_transaction where msid ='"
					+ minNo + "') AND status !='S'";

			dBresults = stmt.executeQuery(queryUpdateStatusMsid);

			dBresults.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateEsnInactive(String esnVal) throws Exception {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);

			stmt = conn.createStatement();
			String queryUpdateinActiveESN = "UPDATE table_part_inst SET X_PART_INST_STATUS = '59',STATUS2X_CODE_TABLE = (select objid from sa.table_x_code_table where x_code_number = '59' )WHERE PART_SERIAL_NO = '"
					+ esnVal + "'";

			dBresults = stmt.executeQuery(queryUpdateinActiveESN);
			conn.commit();
			System.out.println("Committed");
			dBresults.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateEsnInvalid(String esnVal) throws Exception {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);

			stmt = conn.createStatement();
			String queryUpdateinvalidESN = "UPDATE table_part_inst SET X_PART_INST_STATUS = '59',STATUS2X_CODE_TABLE = (select objid from sa.table_x_code_table where x_code_number = '56' )WHERE PART_SERIAL_NO = '"
					+ esnVal + "'";

			dBresults = stmt.executeQuery(queryUpdateinvalidESN);
			conn.commit();
			System.out.println("Committed");
			dBresults.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void doDeactivate(String esn,String min,String reason) throws InstantiationException, IllegalAccessException, ClassNotFoundException,
			FileNotFoundException, IOException {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);
			stmt = conn.createStatement();			
			String deActive = "{call SA.SERVICE_DEACTIVATION.DEACTSERVICE(?,?,?,?,?,?,?,?,?,?)}";			
			cstmt = conn.prepareCall(deActive);
//			String esn="100000012039086";
//			String min="3056044899";
//			String reason="PORT OUT";
			cstmt.setString(1, "PAST_DUE_BATCH");
			cstmt.setString(2, NULL);
			cstmt.setString(3, esn);//minVal
			cstmt.setString(4, min);
			cstmt.setString(5, reason);
			cstmt.setInt(6, 0);
			cstmt.setString(7, NULL);
			cstmt.setString(8, "true");
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);			
			cstmt.executeUpdate();
			
			System.out.println(cstmt.getString(9));
			System.out.println(cstmt.getString(10));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	// ====
	public static String callMIN(String strESN) throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, FileNotFoundException, IOException {
		try {
			Properties testConfig = new Properties();
			testConfig.load(new FileInputStream("TestConfig.properties"));
			String Db_Url = testConfig
					.getProperty(System.getProperty("environment", testConfig.getProperty("environment")) + ".Db_Url");
			String DB_Username = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Username");
			String DB_Password = testConfig.getProperty(
					System.getProperty("environment", testConfig.getProperty("environment")) + ".DB_Password");

			String getMIN = "{ ? = call sa.clear_tnumber(?,?)}";
			Class.forName(jdbcdriver).newInstance();
			conn = DriverManager.getConnection(Db_Url, DB_Username, DB_Password);
			cstmt = conn.prepareCall(getMIN);
			try {
				/*
				 * set serveroutput on
			declare 
			esn varchar2(30) := '100000001091833';--ESN NUMBER
			p_area_code varchar2(10) := '33178';-- Area CODE
			begin
			dbms_output.put_line(sa.clear_tnumber (trim(esn), p_area_code) );
			commit;
			end;	
				 */
				cstmt = conn.prepareCall(getMIN);
				cstmt.registerOutParameter(1, Types.VARCHAR);
				cstmt.setString(2, strESN);
				cstmt.setString(3, "305");
				cstmt.executeUpdate();
				strMinVal = cstmt.getString(1);
				for (int i = 0; i < 20; i++) {
					if (!strMinVal.contains("IS ATTACHED TO MIN")) {
						Thread.sleep(1000);
						cstmt.executeUpdate();
						strMinVal = cstmt.getString(1);
						System.out.println(strMinVal);
						if (strMinVal.contains("IS ATTACHED TO MIN")) {
							break;
						}
					}
				}
				System.out.println(strMinVal);
				minVal = strMinVal.substring(35, 45);

				Thread.sleep(10000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return minVal;
	}
	


	


}
