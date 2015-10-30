package fdms.messenger;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;

import com.aldorsolutions.webfdms.beans.DbServices;
import com.aldorsolutions.webfdms.beans.DbSurvivor;
import com.aldorsolutions.webfdms.beans.DbUserSession;
import com.aldorsolutions.webfdms.beans.DbVeteran;
import com.aldorsolutions.webfdms.beans.DbVitalsDeceased;
import com.aldorsolutions.webfdms.beans.DbVitalsFirstCall;
import com.aldorsolutions.webfdms.beans.DbVitalsSpouse;
import com.aldorsolutions.webfdms.beans.FdmsDb;
import com.aldorsolutions.webfdms.database.DatabaseTransaction;
import com.aldorsolutions.webfdms.util.CalculateAge;
import com.aldorsolutions.webfdms.util.FormatDate;

public class Messenger {
	private Logger logger = Logger.getLogger(Messenger.class.getName());
	DbUserSession sessionUser = null;

	
	public Messenger(DbUserSession sessionUser) {
		super();
		this.sessionUser = sessionUser;
	}


	public CaseProfile loadMessengerData(int vitalsId) {
		DatabaseTransaction t = null;
    DeceasedAndFamilyInfo familyInfo = null;
    MilitaryServiceInfo militaryInfo=null;
    ServiceInfo serviceInfo = null ;
    CaseProfile caseProfile = null;
    GregorianCalendar birth = null;
    GregorianCalendar death = null;

    familyInfo = new DeceasedAndFamilyInfo();
    militaryInfo = new MilitaryServiceInfo();
    serviceInfo = new ServiceInfo();
    String catholicOnlyInfo = null;
    caseProfile = new CaseProfile(familyInfo, serviceInfo, catholicOnlyInfo, militaryInfo);
    
    try {
    	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
    	DbVitalsDeceased vitals = FdmsDb.getInstance().getVitalsDeceased(t, vitalsId);
    	DbVitalsFirstCall firstCall = FdmsDb.getInstance().getVitalsFirstCall(t, vitalsId);
    	DbSurvivor surviors[] = FdmsDb.getInstance().getSurvivorsForID(t, vitalsId, "SequenceNumber");
    	DbServices services = FdmsDb.getInstance().getServices(t, vitalsId);
    	DbSurvivor pallBearer[] = FdmsDb.getInstance().getPallBearers(t, vitalsId); //, "SequenceNumber");
    	DbVeteran veteran = FdmsDb.getInstance().getVeteran(t, vitalsId);
    	DbVitalsSpouse spouse = FdmsDb.getInstance().getVitalsSpouse(t, vitalsId);
    	
    	familyInfo.setTitle(vitals.getDecmrmrs());
    	familyInfo.setFirstName(vitals.getDecFName());
    	familyInfo.setMiddleName(vitals.getDecMName());
    	familyInfo.setLastName(vitals.getDecLName());
      familyInfo.setSuffix(vitals.getSuffix());
      familyInfo.setFullName(vitals.getDecFullName());
      familyInfo.setDateBorn(vitals.getDateOfBirth());
      familyInfo.setPlaceOfBirth(vitals.getBirthPlace());
      familyInfo.setDateDied(vitals.getDateOfDeath());
      
      if(firstCall != null) {
        familyInfo.setPlaceOfDeath(firstCall.getPlaceDeath());
      }
      
      if (vitals.getDateOfBirth() != null && vitals.getDateOfBirth().trim().length() > 7) {
	       birth = new GregorianCalendar(FormatDate.getYearFromMMDDYYYY(vitals.getDateOfBirth()), 
	      		FormatDate.getMonthFromMMDDYYYY(vitals.getDateOfBirth()), FormatDate.getDayFromMMDDYYYY(vitals.getDateOfBirth()));
      }
      if (vitals.getDateOfDeath() != null && vitals.getDateOfDeath().trim().length() > 7) {
	       death = new GregorianCalendar(FormatDate.getYearFromMMDDYYYY(vitals.getDateOfDeath()), 
	      		FormatDate.getMonthFromMMDDYYYY(vitals.getDateOfDeath()), FormatDate.getDayFromMMDDYYYY(vitals.getDateOfDeath()));
      }
      
      if (birth != null && death != null) {
	      CalculateAge age = new CalculateAge(birth, death);
	      familyInfo.setAgeYears(age.getAgeDifference(2));
	      familyInfo.setAgeMonths(age.getAgeDifference(1));
	      familyInfo.setAgeDays(age.getAgeDifference(3));
      }
      
      String father = buildName(null, vitals.getFatherFirstName(), vitals.getFatherMiddleName(), 
      		vitals.getFatherLastName());
      familyInfo.setFather(father);
      familyInfo.setDateFatherBorn(vitals.getFatherBirthday());
      String mother = buildName(null, vitals.getMotherFirstName(), vitals.getMotherMiddleName(), 
      		vitals.getMotherLastName());
      familyInfo.setMother(mother);
      familyInfo.setDateMotherBorn(vitals.getMotherBirthday());
      if(spouse != null) {
        familyInfo.setSpouse(spouse.getFullName());
      }
                 
      if(surviors != null && surviors.length > 0) {
        ArrayList <String> surviorList = new ArrayList <String> ();
        for(int i = 0; i < surviors.length; i++) {
          DbSurvivor dbs = surviors[i];
          if(dbs.getISeqKey() > 0 && dbs.getISeqKey() < 1000 && dbs.getCSurvivorFName().length() > 0) {
            String name = buildName(dbs.getCSurvivorSalutation(), dbs.getCSurvivorFName(), dbs.getCSurvivorMName(), dbs.getCSurvivorLName());
            if(name != null) {
              surviorList.add(name.toString());
            }
          }
        }

        if(surviorList.size() > 0) {
          familyInfo.setFamilyMembers(surviorList);
        }
      }
      
      if(services != null) {
          serviceInfo.setHeldAt(services.getCSrvPlace());
          serviceInfo.setServiceCity(services.getCSrvPlaceCity());
          serviceInfo.setServiceDate(FormatDate.YMDtoMMDDYYYY(vitals.getServiceDateKey()));
          serviceInfo.setServiceHour(services.getServiceTime());
          serviceInfo.setClergy1(services.getCSrvMinister1());
          serviceInfo.setClergy2(services.getCSrvMinister2());
          serviceInfo.setSermonNotes(services.getCSrvSpecialService());
      }
      
      serviceInfo.setServiceDate(FormatDate.YMDtoMMDDYYYY(vitals.getServiceDateKey()));
      if(pallBearer != null && pallBearer.length > 0) {
        ArrayList <String> pallBearerList = new ArrayList <String> ();
        for(int i = 0; i < pallBearer.length; i++) {
          DbSurvivor dbs = pallBearer[i];
          if(dbs.getCSurvivorFName().length() > 0) {
            String name = buildName(dbs.getCSurvivorSalutation(), dbs.getCSurvivorFName(), dbs.getCSurvivorMName(), dbs.getCSurvivorLName());
            if(name != null) {
              pallBearerList.add(name.toString());
            }
          }
        }

        if(pallBearerList.size() > 0) {
          serviceInfo.setBearers(pallBearerList);
        }
      }
      
      if(pallBearer != null && pallBearer.length > 0) {
        ArrayList <String> pallBearerList = new ArrayList <String> ();
        for(int i = 0; i < pallBearer.length; i++) {
          DbSurvivor dbs = pallBearer[i];
          if(dbs.getCSurvivorFName().length() > 0) {
            String name = buildName(dbs.getCSurvivorSalutation(), dbs.getCSurvivorFName(), dbs.getCSurvivorMName(), dbs.getCSurvivorLName());
            if(name != null) {
              pallBearerList.add(name.toString());
            }
          }
        }

        if(pallBearerList.size() > 0) {
          serviceInfo.setBearers(pallBearerList);
        }
      }
      
      if(services != null && services.getCSrvSongs1().length() > 0){
        String songArray[] = services.getCSrvSongs1().split("\n");
        ArrayList <String> songs = new ArrayList <String> ();
        for(int a = 0; a < songArray.length; a++) {
          String song = songArray[a].trim();
          if(song.length() > 0) {
            songs.add(song);
          }
        }

        if(songs.size() > 0) {
          serviceInfo.setMusicSelections(songs);
        }
      }
      
      // Newcomer wanted the Organist to play on the song list.
      if (services != null && services.getCSrvOrganist() != null && services.getCSrvOrganist().length() > 0) {
      	String organist = "Organist - " + services.getCSrvOrganist();
        List <String> songs = serviceInfo.getMusicSelections();
        if (songs == null) {
        	songs = new ArrayList <String> (); 
        }
      	
        songs.add(organist);
      }

      // Newcomer wanted the Vocalist to play on the song list.
      if (services != null && services.getCSrvSoloist() != null && services.getCSrvSoloist().length() > 0) {
      	String vocalist = "Vocalist - " + services.getCSrvSoloist();
        List <String> songs = serviceInfo.getMusicSelections();
        if (songs == null) {
        	songs = new ArrayList <String> (); 
        }
      	
        songs.add(vocalist	);
      }

      if(services != null && services.getCSrvSpecialInstructions().length() > 0) {
        String instructionArray[] = services.getCSrvSpecialInstructions().split("\n");
        ArrayList <String> instructions = new ArrayList <String> ();
        for(int a = 0; a < instructionArray.length; a++) {
          String song = instructionArray[a].trim();
          if(song.length() > 0) {
            instructions.add(song);
          }
        }

        if(instructions.size() > 0) {
          serviceInfo.setSpecialSelections(instructions);
        }
      }
      
      String clergy = new String();
      if(services != null && services.getCSrvMinister1().length() > 0) {
          clergy = services.getCSrvMinister1();
      }
      
      if(services != null && services.getCSrvMinister2().length() > 0) {
        if(clergy.length() > 0) {
          clergy = (new StringBuilder(String.valueOf(clergy))).append(", ").toString();
        }
        clergy = (new StringBuilder(String.valueOf(clergy))).append(services.getCSrvMinister2()).toString();
      }
      
      if(clergy.length() > 0) {
        serviceInfo.setCeremonyBy(clergy);
      }
      
      if(firstCall != null) {
        serviceInfo.setDispositionPlace(firstCall.getDispositionPlace());
        serviceInfo.setDisposition(firstCall.getDispositionMethod());
        serviceInfo.setDispositionCity(firstCall.getDispositionCity());
        serviceInfo.setDispositionCounty(firstCall.getDispositionCounty());
        serviceInfo.setDispositionState(firstCall.getDispositionState());
      }
      
      if(services != null) {
	      serviceInfo.setSection(services.getCSrvCemSection());
	      serviceInfo.setBlock(services.getCSrvCemGrave());
	      serviceInfo.setLot(services.getCSrvCemLot());
      }
      
      if(firstCall != null) {
        Date dispDate = FormatDate.convertMMDDYYYYToDateYYMMDD(firstCall.getDispositionDate());
        serviceInfo.setBuriedMonth(FormatDate.convertDateToMonthString(dispDate));
        serviceInfo.setBuriedYear(FormatDate.convertDateToYYYYString(dispDate));
      }
      
      String branch = new String();
      if (veteran != null) {
        if(veteran.getFlag_ArmyBox() == 1 || veteran.getHead_BranchAR() == 1) {
            branch = (new StringBuilder(String.valueOf(branch))).append("Army").toString();
        }
        
        if(veteran.getFlag_NavyBox() == 1 || veteran.getHead_BranchNA() == 1) {
         if(branch.length() > 0) {
           branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
         }
         branch = (new StringBuilder(String.valueOf(branch))).append("Navy").toString();
        }
        
        if(veteran.getFlag_AirForceBox() == 1 || veteran.getHead_BranchAF() == 1) {
          if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
          }
          branch = (new StringBuilder(String.valueOf(branch))).append("Air Force").toString();
        }
        
        if(veteran.getFlag_MarineBox() == 1 || veteran.getHead_BranchMC() == 1) {
          if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
          }
          branch = (new StringBuilder(String.valueOf(branch))).append("Marine Corps").toString();
        }
        
        if(veteran.getFlag_CoastGaurdBox() == 1 || veteran.getHead_BranchCG() == 1) {
          if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
          }
          branch = (new StringBuilder(String.valueOf(branch))).append("Coast Guard").toString();
        }
        
        if(veteran.getFlag_SelReserveBox() == 1) {
          if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
        	}
          branch = (new StringBuilder(String.valueOf(branch))).append("Selected Reserve").toString();
        }

        if(veteran.getFlag_OtherBranchBox() == 1 && veteran.getFlag_OtherBranchOfService().length() > 0) {
        	if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
        	}
          branch = (new StringBuilder(String.valueOf(branch))).append(veteran.getFlag_OtherBranchOfService()).toString();
        }
        
        if(veteran.getHead_BranchAC() == 1) {
          if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
        	}
          branch = (new StringBuilder(String.valueOf(branch))).append("Army Air Force").toString();
        }
        
        if(veteran.getHead_BranchMM() == 1) {
          if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
        	}
          branch = (new StringBuilder(String.valueOf(branch))).append("Merchant Marine").toString();
        }
        
        if(veteran.getHead_BranchOther() == 1 && veteran.getHead_BranchSpecify().length() > 0) {
          if(branch.length() > 0) {
            branch = (new StringBuilder(String.valueOf(branch))).append(", ").toString();
        	}
          branch = (new StringBuilder(String.valueOf(branch))).append(veteran.getHead_BranchSpecify()).toString();
        }
        
        if(branch != null && branch.trim().length() > 0) {
          militaryInfo.setBranchOfService(branch);
        }
        
        if(veteran.getFlag_SerialNo() != null && veteran.getFlag_SerialNo().trim().length() > 0) {
          militaryInfo.setSerialNumber(veteran.getFlag_SerialNo());
        }
        
        if(veteran.getHead_HighestRank() != null && veteran.getHead_HighestRank().trim().length() > 0) {
          militaryInfo.setOrganizationAndRank(veteran.getFlag_EnlistmentDate());
        }
        
        if(veteran.getFlag_EnlistmentDate() != null && veteran.getFlag_EnlistmentDate().trim().length() > 0) {
          militaryInfo.setDateEntered(veteran.getFlag_EnlistmentDate());
        }
        
        if(veteran.getFlag_DischargeDate() != null && veteran.getFlag_DischargeDate().trim().length() > 0) {
          militaryInfo.setDateDischarged(veteran.getFlag_DischargeDate());
        }
        
        String decorations = new String();
        if(veteran.getHead_ValorMOH() == 1) {
          decorations = (new StringBuilder(String.valueOf(decorations))).append("Medal Of Honor").toString();
        }
        
        if(veteran.getHead_ValorDSC() == 1) {
          if(decorations.length() > 0) {
            decorations = (new StringBuilder(String.valueOf(decorations))).append(", ").toString();
          }
          decorations = (new StringBuilder(String.valueOf(decorations))).append("DST SVC Cross").toString();
        }
        
        if(veteran.getHead_ValorNC() == 1) {
          if(decorations.length() > 0) {
            decorations = (new StringBuilder(String.valueOf(decorations))).append(", ").toString();
          }
          decorations = (new StringBuilder(String.valueOf(decorations))).append("Navy Cross").toString();
        }
        
        if(veteran.getHead_ValorAFC() == 1) {
          if(decorations.length() > 0) {
            decorations = (new StringBuilder(String.valueOf(decorations))).append(", ").toString();
          }
          decorations = (new StringBuilder(String.valueOf(decorations))).append("Air Force Cross").toString();
        }
        
        if(veteran.getHead_ValorSS() == 1) {
          if(decorations.length() > 0) {
            decorations = (new StringBuilder(String.valueOf(decorations))).append(", ").toString();
          }
          decorations = (new StringBuilder(String.valueOf(decorations))).append("Silver Star").toString();
        }
        
        if(veteran.getHead_ValorBSM() == 1) {
          if(decorations.length() > 0) {
            decorations = (new StringBuilder(String.valueOf(decorations))).append(", ").toString();
          }
          decorations = (new StringBuilder(String.valueOf(decorations))).append("Bronze Star Medal").toString();
        }
        
        if(veteran.getHead_ValorPH() == 1) {
          if(decorations.length() > 0) {
            decorations = (new StringBuilder(String.valueOf(decorations))).append(", ").toString();
          }
          decorations = (new StringBuilder(String.valueOf(decorations))).append("Purple Heart").toString();
        }
        
        if (veteran.getHead_ValorOther() == 1 && veteran.getHead_ValorOtherSpecify() != null && 
        	  veteran.getHead_ValorOtherSpecify().length() > 0) {
          if(decorations.length() > 0) {
            decorations = (new StringBuilder(String.valueOf(decorations))).append(", ").toString();
          }
          decorations = (new StringBuilder(String.valueOf(decorations))).append(veteran.getHead_ValorOtherSpecify()).toString();
        }
        
        if(decorations != null && decorations.trim().length() > 0) {
          militaryInfo.setDecorationsAndCitations(decorations);
        }
      }
    } catch(Exception e) {
      logger.error("Exception in loadMessengerData() : ", e);
      e.printStackTrace();
      caseProfile = null;  
    } finally {
    	if(t != null) {
    		t.closeConnection();
    	}
    }
  	
    return caseProfile;
  }
      
      
	public void sendXMLResponseToClient(CaseProfile caseProfile, int vitalsId, HttpServletResponse response) {
		DatabaseTransaction t = null;
     try {
    	t = (DatabaseTransaction)DatabaseTransaction.getTransaction(sessionUser);
    	DbVitalsDeceased vitals = FdmsDb.getInstance().getVitalsDeceased(t, vitalsId);
    	
    	JAXBContext context = JAXBContext.newInstance(CaseProfile.class);
	    Marshaller marshaller = context.createMarshaller();
	    marshaller.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
      
      StringWriter xmlWriter = new StringWriter();
      marshaller.marshal(caseProfile, xmlWriter);
      StringBuffer fileName = new StringBuffer(vitals.getDecLName());
      fileName.append(", ");
      fileName.append(vitals.getDecFName().charAt(0));
      fileName.append("-");
      fileName.append(FormatDate.convertUnformattedMDYtoYMD(vitals.getDateOfDeath()));
      fileName.append(".xml");
      response.setContentType("application/force-download");
      response.setHeader("Content-disposition", (new StringBuilder("attachment; filename=\"")).append(fileName.toString()).append("\"").toString());
      response.setHeader("Accept-Header", String.valueOf(xmlWriter.toString().length()));
      ServletOutputStream out = response.getOutputStream();
      out.write(xmlWriter.toString().getBytes());
      out.flush();
      out.close();
    } catch(Exception e) {
      logger.error("Exception in sendXMLResponseToClient() : ", e);
      e.printStackTrace();
    } finally {
    	if(t != null) {
    		t.closeConnection();
    	}
    }
  }

	
	private String buildName(String prefix, String firstName, String middleName, String lastName) {
		StringBuffer name = new StringBuffer();
		if (prefix != null && prefix.length() > 0) {
				name.append(prefix);
		}
		
		if (firstName != null && firstName.length() > 0) {
			if (name.length() > 0) {
				name.append(" ");
			}
			name.append(firstName);
		}
		
		if (middleName != null && middleName.length() > 0) {
			if (name.length() > 0) {
				name.append(" ");
			}
			name.append(middleName);
		}
		
		if (lastName != null && lastName.length() > 0) {
			if (name.length() > 0) {
				name.append(" ");
			}
			name.append(lastName);
		}
		
		if (name.length() > 0) {
			return name.toString();
		} else {
			return null;
		}
	}

	
	/**
	 * We had to create a hack of a routine to create a date where we would set the timezone to -00:00 
	 * so this is that routine.  
	 * @param date
	 * @return
	 */
	public static String createXMLDateString(String date) {
		String returnDate;
		Date tempDate = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			if (date != null && date.length() > 0) {
				SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");
				tempDate = formatter.parse(date); 
	
				format.setLenient(false);
				returnDate = format.format(tempDate);
			} else {
				returnDate = null;
			}
		} catch (Exception e) {
			returnDate = null;
		}
		
		return returnDate;
	}
}
