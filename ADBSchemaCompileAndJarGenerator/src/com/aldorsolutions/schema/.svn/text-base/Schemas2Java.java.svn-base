package com.aldorsolutions.schema;

import org.apache.axis2.schema.XSD2Java;

public class Schemas2Java {
	
	public static void main (String[] args) throws Exception{
		if(args != null && args.length == 2){
			if(args[0].contains(",")){
				String[] schemas = args[0].split(",");
				String[] params = new String[2];
				params[1] = args[1];
				for(String schema : schemas){
					params[0] = schema;
					XSD2Java.main(params);
				}
				
			}else{
				XSD2Java.main(args);
			}
		}else{
			System.out.println("Argument1 - Source schema file names separated by ',' (comma) \nArgument2 - Output folder name");
		}
	}

}
