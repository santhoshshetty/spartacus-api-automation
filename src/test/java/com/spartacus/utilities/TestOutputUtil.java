package com.spartacus.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.LogManager;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestOutputUtil {

	/** The execution start time. */
	public static String executionStartTime;

	/** The execution start date. */
	public static String executionStartDate;

	/** The browser directory. */
	public static String apiDirectory;

	/** The date directory. */
	public static String dateDirectory;

	/** The timestamp directory. */
	public static String timestampDirectory;

	/** The screenshot directory. */
	public static String screenshotDirectory;

	/** The extent report directory. */
	public static String extentReportDirectory;

	/** The log directory. */
	public static String logDirectory;

	/** The log file path. */
	public static String logFilePath;

	public static String baseProjectPath = System.getProperty("user.dir");

	/** The logger. */
	private static final Logger LOG = LoggerFactory.getLogger(TestOutputUtil.class);

	public static HashMap<Object, Object> folderPath = new HashMap<Object, Object>();

	public HashMap<Object, Object> createOutputFolder(String executionType) throws Exception {
		try {
			if (null == executionStartDate) {
				Date curDate = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				String DateToStr = format.format(curDate);
				format = new SimpleDateFormat("dd-MM-yyyy HH mm ss");
				DateToStr = format.format(curDate);
				System.out.println("Complete Date is ::" + DateToStr);
				executionStartDate = DateToStr.substring(0, 10);
				System.out.println("Date is ::" + executionStartDate);
				executionStartTime = DateToStr.substring(11, 19);
				System.out.println(executionStartTime);
				executionStartTime = executionStartTime.replace(APIExecutionConstants.SPACE.charAt(0),
						APIExecutionConstants.HYPHEN.charAt(0));
				System.out.println(executionStartTime);
				String baseDirectory = baseProjectPath.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
						.concat(APIExecutionConstants.TEST_RESULTS);
				if (null != baseDirectory) {
					apiDirectory = baseDirectory.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
							.concat(executionType);
					System.out.println("browser directory is directoryName" + apiDirectory);
					createDirTree(apiDirectory);
					if (null != apiDirectory) {
						// creating date folder
						dateDirectory = apiDirectory.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
								.concat(executionStartDate);
						System.out.println("dateDirectory directory is directoryName" + dateDirectory);
						createDirTree(dateDirectory);
						if (null != dateDirectory) {
							// creating time stamp folder
							timestampDirectory = dateDirectory.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
									.concat(executionStartTime);
							System.out.println("timestamp directory is directoryName" + timestampDirectory);
							createDirTree(timestampDirectory);
							if (null != timestampDirectory) {
								// creating screenshot folder
								screenshotDirectory = timestampDirectory
										.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
										.concat(APIExecutionConstants.SCREENSHOTS);
								System.out.println("screenshot directory is directoryName" + screenshotDirectory);
								createDirTree(screenshotDirectory);

								// creating extentReportDirectory folder
								extentReportDirectory = timestampDirectory
										.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
										.concat(APIExecutionConstants.EXTENT_REPORT);
								System.out.println(
										"extent report dir directory is directoryName" + extentReportDirectory);
								createDirTree(extentReportDirectory);

								// creating extentReportDirectory folder
								logDirectory = timestampDirectory.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
										.concat(APIExecutionConstants.LOGS);
								System.out.println("log directory is directoryName" + logDirectory);
								createDirTree(logDirectory);
								if (null != logDirectory) {
									// Creating log file
									logFilePath = logDirectory.concat(APIExecutionConstants.DOUBLE_FORWARD_SLASH)
											.concat(APIExecutionConstants.LOG_FILE_NAME);
									System.out.println("log file name is ::" + logFilePath);
									createNewFile(logFilePath);
									try {
										Properties properties = new Properties();
										InputStream configStream = new FileInputStream(
												baseProjectPath.concat(APIExecutionConstants.LOG4J_FILE_PATH));
										properties.load(configStream);
										configStream.close();
										properties.setProperty("log4j.appender.R.File", logFilePath);
										LogManager.getLogManager().reset();
										PropertyConfigurator.configure(properties);

									} catch (Exception exception) {
										System.out.println("Error in finding the log file::" + exception.getMessage());
									}

								}
							}
						}
					}
				}
				folderPath.put("apiDirectory", apiDirectory);
				folderPath.put("DateStampDirectory", dateDirectory);
				folderPath.put("TimeStampDirectory", timestampDirectory);
				folderPath.put("LogDirectory", logDirectory);
				folderPath.put("ExtentDirectory", extentReportDirectory);
				folderPath.put("ScreenshotDirectory", screenshotDirectory);
			}
		} catch (

		Exception exception) {
			System.out.println("Error occured while creating files and directories" + exception.getMessage());
		}
		return folderPath;
	}

	/**
	 * Creates the dir tree.
	 *
	 * @param directoryTreeName the directory tree name
	 */
	public static void createDirTree(String directoryTreeName) {
		try {

			File dir = new File(directoryTreeName);
			if (!dir.exists()) {
				if (dir.mkdirs()) {
				} else {
				}

			}
		} catch (Exception exception) {
			LOG.info("Error  Exception in file::" + directoryTreeName);
			LOG.info("Error is ::" + exception.getMessage());
			exception.printStackTrace();
		}
	}

	/**
	 * Creates the new file.
	 *
	 * @param filePath the file path
	 */
	public static void createNewFile(String filePath) {
		try {
			LOG.info("file path is " + filePath);

			File file = new File(filePath);

			if (file.getParentFile().exists()) {
				LOG.info("Directory is present!" + file.getParent());
				file.createNewFile();
			} else {
				LOG.info("creating directory!" + file.getParent());
				createDirTree(file.getParent());
				file.createNewFile();
			}

		} catch (

		Exception exception) {
			LOG.info("Error  Exception in file::" + filePath);
			LOG.info("Error is ::" + exception.getMessage());
			exception.printStackTrace();
		}
	}
}
