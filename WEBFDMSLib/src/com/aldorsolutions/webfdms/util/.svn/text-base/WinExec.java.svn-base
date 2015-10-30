package com.aldorsolutions.webfdms.util;

import org.apache.log4j.Logger;

/**
 * Encapsulate method to easily shell an OS command or run a known document type.
 * Creation date: (8/12/2002 11:05:32 AM)
 * @author:
 */
public class WinExec {
    
    private static Logger logger = Logger.getLogger(WinExec.class.getName());
    /**
     * WinExec constructor comment.
     */
    public WinExec() {
        super();
    }
    /**
     * Run an external document (like doc.HTML)
     * Adapted from JavaWorld article at:
     * http://www.javaworld.com/javaworld/jw-12-2000/jw-1229-traps.html
     * Creation date: (8/12/2002 11:06:25 AM)
     * @return int
     * @param cmds java.lang.String
     */
    public final static int shell(String incommand) {
        if (incommand==null && incommand.compareTo(" ")<=0) {
            // AppLog.trace("USAGE: fdms.util.WinExec.Shell(<cmd>)");
            return(1);
        }
        
        try {
            String osName = System.getProperty("os.name" );
            String[] cmd = new String[3];
            
            if( osName.equals( "Windows NT" ) ) {
                cmd[0] = "cmd.exe" ;
                cmd[1] = "/C" ;
                cmd[2] = incommand;
            }
            else if( osName.equals( "Windows 95" ) ) {
                cmd[0] = "command.com" ;
                cmd[1] = "/C" ;
                cmd[2] = incommand;
            }
            
            Runtime rt = Runtime.getRuntime();
            //AppLog.trace("Execing " + cmd[0] + " " + cmd[1] + " " + cmd[2]);
            Process proc = rt.exec(cmd);
            // any error message?
            StreamGobbler errorGobbler = new
            StreamGobbler(proc.getErrorStream(), "ERROR");
            
            // any output?
            StreamGobbler outputGobbler = new
            StreamGobbler(proc.getInputStream(), "OUTPUT");
            
            // kick them off
            errorGobbler.start();
            outputGobbler.start();
            
            // any error???
            int exitVal = proc.waitFor();
            //AppLog.trace("WinExec.shell ExitValue: " + exitVal);
            return exitVal;
        } catch (Throwable t) {
            logger.error("WinExec.shell() error:", t);
        }
        return 0;
    }
}
