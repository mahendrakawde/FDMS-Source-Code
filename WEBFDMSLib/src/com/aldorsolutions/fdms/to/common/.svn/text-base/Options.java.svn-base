package com.aldorsolutions.fdms.to.common;

import java.util.ArrayList;
import java.util.List;

import com.aldorsolutions.fdms.exception.InvalidOperationException;

public class Options {
	
	private List<StringOption> stringOptions = new ArrayList<StringOption>();
	private List<IntOption> intOptions = new ArrayList<IntOption>();
	
	public Options(){}
	
	public void addStringOption(String option, String value)throws InvalidOperationException{
		if(isStringOptions() || isEmpty()){
			stringOptions.add(new StringOption(option, value));
			return;
		}
		throw new InvalidOperationException();
	}
	
	public void addIntOption(String option, int value)throws InvalidOperationException{
		if(isIntOptions() || isEmpty()){
			intOptions.add(new IntOption(option, value));
			return;
		}
		throw new InvalidOperationException();
	}
	
	public void clear(){
		if(isIntOptions()){
			intOptions.clear();
			
		}
		if(isStringOptions()){
			stringOptions.clear();
		}
	}
	
	public List<StringOption> getStringOptions(){
		return stringOptions;
	}
	public List<IntOption> getIntOptions(){
		return intOptions;
	}
	
	public boolean isStringOptions(){
		return stringOptions.size()>0;
	}
	
	public boolean isIntOptions(){
		return intOptions.size()>0;
	}
	
	public boolean isEmpty(){
		return !(isStringOptions() || isIntOptions());
	}
	public class StringOption{
		
		private String option;
		private String value;
		public StringOption(String option, String value) {
			super();
			this.option = option;
			this.value = value;
		}
		public String getOption() {
			return option;
		}
		public void setOption(String option) {
			this.option = option;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public class IntOption{
		
		private String option;
		private int value;
		public IntOption(String option, int value) {
			super();
			this.option = option;
			this.value = value;
		}
		public String getOption() {
			return option;
		}
		public void setOption(String option) {
			this.option = option;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
	}
}
