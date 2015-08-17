package com.dbll.util.shell;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ExcuteShell {
	
	public final static String VAR_START = "start";
	public final static String VAR_STOP = "stop";
	public final static String VAR_STATUS = "status";
	public final static String VAR_RESTART = "restart";
	
	public static synchronized String excute(String sh, String var) throws IOException,
			InterruptedException {
		Runtime runtime = Runtime.getRuntime();
		
		Process chmodProcess = runtime.exec("chmod 777" + sh);
		chmodProcess.waitFor();
		
		Process process = runtime.exec("/bin/sh " + sh + " " + var);
		process.waitFor();
		
		InputStream input = process.getInputStream();
		InputStreamReader inputReader = new InputStreamReader(input);
		LineNumberReader lineReader = new LineNumberReader(inputReader);
		
		boolean flag = true;
		
		StringBuffer outMsg = new StringBuffer("==================excute===================");
		
		while (flag) {
			String readLine = lineReader.readLine();
			if (readLine == null) {
				flag = false;
			}else{
				outMsg.append("\n");
				outMsg.append(readLine);
			}
		}
		
		outMsg.append("\n");
		outMsg.append("==================excute-end===================");
		
		return outMsg.toString();
	}
}
