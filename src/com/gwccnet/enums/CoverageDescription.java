package com.gwccnet.enums;


public enum CoverageDescription
{
	INLAND_MARINE("Inland Marine","INMAR","90",""),
	CARGO("Cargo","CARGO","91",""),
	WORK_COMP_MEDICAL_PAYMENTS("Work Comp Medical Payments","WCMED","86","914"),
	WORK_COMP_INDEMNITY ("Work Comp Indemnity","WCIND","86","924"),
	COMPREHENSIVE("Comprehensive","CMP","66",""),
	GENERAL_LIABILITY_BODILY_INJURY("General Liability Bodily Injury","GLBI","86",""),
	GENERAL_LIABILITY_PHYS_DAM("General Liability Physical Damage","GLPD","77",""),
	GENERAL_LIABILITY_MEDICAL_PAYMENTS("General Liability Medical Payments","GLMP","78",""),
	AUTO_BODILY_INJURY("Auto Liability Bodily Injury","AUTBI","70",""),
	AUTO_MEDICAL_PAYMENTS("Auto Medical Payments","AUTMP","74",""),
	AUTO_UNDERINSURED_MOTORIST("Auto Underinsured Motorist","AUTUM","75",""),
	AUTO_NO_FAULT("Auto No Fault","AUTNF","79",""),
	AUTO_PHYSICAL_DAMAGE("Auto Liability Property Damage","AUTPD","71",""),
	AUTO_COMPREHENSIVE("Auto Comprehensive","ACOMP","72",""),
	AUTO_COLLISION("Auto Collision","ACOLL","73",""),
	SURETY("Surety","SURTY","82",""),
	FIDELITY("Fidelity","FIDTY","83",""),
	BURGLARY("Burglary","BURG","84",""),
	UNKNOWN_COVERAGE("Unknown Coverage","UNKNOWN","UK","");
	
	@Override
	public String toString()
	{
		return description.toString();
	}
	private String description;
	private String shortName;
	private String majorPeril;
	private String coverageCode;
	
	private CoverageDescription(String description, String shortName,String majorPeril,String coverageCode)
	{
	
		this.description = description;
		this.shortName = shortName;
		this.coverageCode = coverageCode;
		this.majorPeril = majorPeril;
		
	}
	
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getShortName()
	{
		return shortName;
	}
	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}
	
	public static CoverageDescription determineCoverage(String peril, String coverageCode)
	{
		CoverageDescription covDesc = UNKNOWN_COVERAGE;
		if (peril.startsWith("90"))
		{
			covDesc = INLAND_MARINE;
		}
		else if (peril.startsWith("91"))
		{
			covDesc = CARGO;
		}
		else if (peril.startsWith("86"))
		{
			if (coverageCode != null)
			{
				if (coverageCode.equals("914"))
				{
					covDesc = WORK_COMP_MEDICAL_PAYMENTS;
				}
				else if (coverageCode.equals("924"))
				{
					covDesc = WORK_COMP_INDEMNITY;
				}
				else
				{
					covDesc = GENERAL_LIABILITY_BODILY_INJURY;
				}
			}
			else
			{
				covDesc = GENERAL_LIABILITY_BODILY_INJURY;
			}
		}
		else if (peril.startsWith("66"))
		{
			covDesc = COMPREHENSIVE;
		}
		else if (peril.startsWith("76"))
		{
			covDesc = CoverageDescription.GENERAL_LIABILITY_BODILY_INJURY;
		} 
		else if (peril.startsWith("77"))
		{
			covDesc = CoverageDescription.GENERAL_LIABILITY_PHYS_DAM;
		}
		else if (peril.startsWith("78"))
		{
			covDesc = CoverageDescription.GENERAL_LIABILITY_MEDICAL_PAYMENTS;
		}		
		else if(peril.startsWith("70"))
		{
			covDesc = AUTO_BODILY_INJURY;
		}
		else if(peril.startsWith("74"))
		{
			covDesc = AUTO_MEDICAL_PAYMENTS;
		}
		else if(peril.startsWith("75"))
		{
			covDesc = AUTO_UNDERINSURED_MOTORIST;
		}		
		else if(peril.startsWith("79"))
		{
			covDesc = AUTO_NO_FAULT;
		}		
		else if(peril.startsWith("70"))
		{
			covDesc = AUTO_BODILY_INJURY;
		}		
		else if(peril.startsWith("71"))
		{
			covDesc = AUTO_PHYSICAL_DAMAGE;
		}		
		else if(peril.startsWith("72"))
		{
			covDesc = AUTO_COMPREHENSIVE;
		}		
		else if(peril.startsWith("73"))
		{
			covDesc = AUTO_COLLISION;
		}		
		else if(peril.startsWith("82"))
		{
			covDesc = SURETY;
		}		
		else if(peril.startsWith("83"))
		{
			covDesc = FIDELITY;
		}
		else if(peril.startsWith("84"))
		{
			covDesc = BURGLARY;
		}		
		return covDesc;
	}
	
	public String getCoverageCode()
	{
		return coverageCode;
	}
	public void setCoverageCode(String coverageCode)
	{
		this.coverageCode = coverageCode;
	}
	public String getMajorPeril()
	{
		return majorPeril;
	}
	public void setMajorPeril(String majorPeril)
	{
		this.majorPeril = majorPeril;
	}
}
