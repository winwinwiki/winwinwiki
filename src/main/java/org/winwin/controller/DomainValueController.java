package org.winwin.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.model.Address;
import org.winwin.model.Organisation;
import org.winwin.model.SDG;
import org.winwin.model.SPI;
import org.winwin.repository.AddressRepository;
import org.winwin.repository.OrganisationRepository;
import org.winwin.repository.SDGRepository;
import org.winwin.repository.SPIRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DomainValueController {
	
	@Autowired
	private SPIRepository spiRepository;

	@Autowired
	private SDGRepository sdgRepository;

	@GetMapping("/domain/spi")
	public List<SPI> getSPI() {
		List<SPI> values = spiRepository.findAll();
		return values;
	}

	@GetMapping("/domain/sdg")
	public List<SDG> getSDG() {
		List<SDG> values = sdgRepository.findAll();
		return values;
	}

	
	@GetMapping("/domain/spi/populate")
	public String putDefaultSPI() {
		String [] values = { "Undernourishment","Nutrition and Basic Medical Care","Basic Human Needs",
				"Depth of food deficit","Nutrition and Basic Medical Care","Basic Human Needs",
				"Maternal mortality rate","Nutrition and Basic Medical Care","Basic Human Needs",
				"Child mortality rate","Nutrition and Basic Medical Care","Basic Human Needs",
				"Deaths from infectious diseases","Nutrition and Basic Medical Care","Basic Human Needs",
				"Access to piped water","Water and Sanitation","Basic Human Needs",
				"Rural access to improved water source","Water and Sanitation","Basic Human Needs",
				"Access to improved sanitation facilities","Water and Sanitation","Basic Human Needs",
				"Availability of affordable housing","Shelter","Basic Human Needs",
				"Access to electricity","Shelter","Basic Human Needs",
				"Quality of electricity supply","Shelter","Basic Human Needs",
				"Indoor air pollution attributable deaths","Shelter","Basic Human Needs",
				"Clothing","Shelter","Basic Human Needs",
				"Housing support","Shelter","Basic Human Needs",
				"Homicide rate","Personal Safety","Basic Human Needs",
				"Level of violent crime","Personal Safety","Basic Human Needs",
				"Perceived criminality","Personal Safety","Basic Human Needs",
				"Political terror","Personal Safety","Basic Human Needs",
				"Traffic deaths","Personal Safety","Basic Human Needs",
				"Emergency response","Personal Safety","Basic Human Needs",
				"Adult literacy rate","Access to Basic Knowledge","Foundations of Wellbeing",
				"Primary school enrollment","Access to Basic Knowledge","Foundations of Wellbeing",
				"Lower secondary school enrollment","Access to Basic Knowledge","Foundations of Wellbeing",
				"Upper secondary school enrollment","Access to Basic Knowledge","Foundations of Wellbeing",
				"Gender parity in secondary enrollment","Access to Basic Knowledge","Foundations of Wellbeing",
				"Mobile telephone subscriptions","Access to Information and Communications","Foundations of Wellbeing",
				"Internet users","Access to Information and Communications","Foundations of Wellbeing",
				"Press Freedom Index","Access to Information and Communications","Foundations of Wellbeing",
				"Life expectancy","Health and Wellness","Foundations of Wellbeing",
				"Premature deaths from non-communicable diseases","Health and Wellness","Foundations of Wellbeing",
				"Obesity rate","Health and Wellness","Foundations of Wellbeing",
				"Outdoor air pollution attributable deaths","Health and Wellness","Foundations of Wellbeing",
				"Suicide rate","Health and Wellness","Foundations of Wellbeing",
				"Care for aging","Health and Wellness","Foundations of Wellbeing",
				"Access to mental health resources","Health and Wellness","Foundations of Wellbeing",
				"Greenhouse gas emissions","Ecosystem Sustainability","Foundations of Wellbeing",
				"Water withdrawals as a percent of resources","Ecosystem Sustainability","Foundations of Wellbeing",
				"Biodiversity and habitat","Ecosystem Sustainability","Foundations of Wellbeing",
				"Political rights","Personal Rights","Opportunity",
				"Freedom of speech","Personal Rights","Opportunity",
				"Freedom of assembly/association","Personal Rights","Opportunity",
				"Freedom of movement","Personal Rights","Opportunity",
				"Private property rights","Personal Rights","Opportunity",
				"Transparency","Personal Rights","Opportunity",
				"Freedom over life choices","Personal Freedom and Choice","Opportunity",
				"Freedom of religion","Personal Freedom and Choice","Opportunity",
				"Early marriage","Personal Freedom and Choice","Opportunity",
				"Satisfied demand for contraception","Personal Freedom and Choice","Opportunity",
				"Corruption","Personal Freedom and Choice","Opportunity",
				"Transportation","Personal Freedom and Choice","Opportunity",
				"Disability support","Personal Freedom and Choice","Opportunity",
				"Employment opportunity","Personal Freedom and Choice","Opportunity",
				"Innovation and Creativity Support","Personal Freedom and Choice","Opportunity",
				"Tolerance for immigrants","Tolerance and Inclusion","Opportunity",
				"Tolerance for homosexuals","Tolerance and Inclusion","Opportunity",
				"Discrimination and violence against minorities","Tolerance and Inclusion","Opportunity",
				"Religious tolerance","Tolerance and Inclusion","Opportunity",
				"Community safety net","Tolerance and Inclusion","Opportunity",
				"Arts and Culture Access","Tolerance and Inclusion","Opportunity",
				"Years of tertiary schooling","Access to Advanced Education","Opportunity",
				"Women's average years in school","Access to Advanced Education","Opportunity",
				"Inequality in the attainment of education","Access to Advanced Education","Opportunity",
				"Globally ranked universities","Access to Advanced Education","Opportunity" };
		for(int i = 0; i < values.length ; i+=3) {
			SPI value = new SPI();
			value.setIndicator(values[i]);
			value.setComponent(values[i+1]);
			value.setDimension(values[i+2]);
			value.setCreatedAt(new Date());
			value.setUpdatedAt(new Date());
			spiRepository.save(value);
		}
		return spiRepository.count()+"";
	}
	
	@GetMapping("/domain/sdg/populate")
	public String addDefaultSDG() {
		String[] values = {
				"1.1 End Extreme Poverty","1.1","End Poverty","1","1. End Poverty",
				"1.2 Reduce Poverty","1.2","End Poverty","1","1. End Poverty",
				"1.3 Social Protection","1.3","End Poverty","1","1. End Poverty",
				"1.4 Equal Access to Goods & Services","1.4","End Poverty","1","1. End Poverty",
				"1.5 Build Resilience","1.5","End Poverty","1","1. End Poverty",
				"1.a Secure Resources for Programs","1.a","End Poverty","1","1. End Poverty",
				"1.b Scalable Policy Frameworks","1.b","End Poverty","1","1. End Poverty",
				"2.1 End Hunger","2.1","End Hunger","2","2. End Hunger",
				"2.2 End Malnutrition","2.2","End Hunger","2","2. End Hunger",
				"2.3 Increase Agricultural Activity & Income","2.3","End Hunger","2","2. End Hunger",
				"2.4 Sustainable Food Systems","2.4","End Hunger","2","2. End Hunger",
				"2.5 Seed Genetic Diversity","2.5","End Hunger","2","2. End Hunger",
				"2.a Investment in Agriculture","2.a","End Hunger","2","2. End Hunger",
				"2.b Reduce Trade Restrictions","2.b","End Hunger","2","2. End Hunger",
				"2.c Food Market Systems","2.c","End Hunger","2","2. End Hunger",
				"3.1 Reduce Maternal Mortality","3.1","Good Health","3","3. Good Health",
				"3.2 End Child Mortality","3.2","Good Health","3","3. Good Health",
				"3.3 End Disease Epidemics","3.3","Good Health","3","3. Good Health",
				"3.4 Reduce Mortality from Treatable Disease","3.4","Good Health","3","3. Good Health",
				"3.5 Treat & Prevent Drug Abuse","3.5","Good Health","3","3. Good Health",
				"3.6 Traffic Safety","3.6","Good Health","3","3. Good Health",
				"3.7 Reproductive Healthcare & Education","3.7","Good Health","3","3. Good Health",
				"3.8 Universal Healthcare","3.8","Good Health","3","3. Good Health",
				"3.9 Reduce Illness from Contamination","3.9","Good Health","3","3. Good Health",
				"3.a Reduce Tobacco Supply & Demand","3.a","Good Health","3","3. Good Health",
				"3.b Research Disease Prevention & Treatment","3.b","Good Health","3","3. Good Health",
				"3.c Health Financing","3.c","Good Health","3","3. Good Health",
				"3.d Risk Management","3.d","Good Health","3","3. Good Health",
				"4.1 Complete Childhood Education","4.1","Quality Education","4","4. Quality Education",
				"4.2 Early Childhood Development","4.2","Quality Education","4","4. Quality Education",
				"4.3 Equal Access to Higher Education","4.3","Quality Education","4","4. Quality Education",
				"4.4 Employable Skills Development","4.4","Quality Education","4","4. Quality Education",
				"4.5 Educational Gender Equity","4.5","Quality Education","4","4. Quality Education",
				"4.6 100% Literacy & Numeracy","4.6","Quality Education","4","4. Quality Education",
				"4.7 Sustainability Education","4.7","Quality Education","4","4. Quality Education",
				"4.a Quality Education Facilities","4.a","Quality Education","4","4. Quality Education",
				"4.b Scholarship Availability","4.b","Quality Education","4","4. Quality Education",
				"4.c Qualified Teacher Training","4.c","Quality Education","4","4. Quality Education",
				"5.1 End Female Discrimination","5.1","Gender Equality","5","5. Gender Equality",
				"5.2 End Female-Targeted Violence","5.2","Gender Equality","5","5. Gender Equality",
				"5.3 End Harmful Female Practices","5.3","Gender Equality","5","5. Gender Equality",
				"5.4 Services for Unpaid Females","5.4","Gender Equality","5","5. Gender Equality",
				"5.5 Gender Equity in Leadership","5.5","Gender Equality","5","5. Gender Equality",
				"5.6 Reproductive Rights and Health Care","5.6","Gender Equality","5","5. Gender Equality",
				"5.a Female Access to Economic Resources","5.a","Gender Equality","5","5. Gender Equality",
				"5.b Female Empowerment Through Technology","5.b","Gender Equality","5","5. Gender Equality",
				"5.c Gender Equity Policies","5.c","Gender Equality","5","5. Gender Equality",
				"6.1 Access to Drinking Water","6.1","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"6.2 Access to Sanitation and Hygiene","6.2","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"6.3 Water Quality","6.3","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"6.4 Efficient Use of Water","6.4","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"6.5 Water Resource Management","6.5","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"6.6 Protect & Restore Water Resources","6.6","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"6.a International Support","6.a","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"6.b Local Support","6.b","Clean Water & Sanitation","6","6. Clean Water & Sanitation",
				"7.1 Universal Energy Access","7.1","Renewable Energy","7","7. Renewable Energy",
				"7.2 Increase Renewable Energy","7.2","Renewable Energy","7","7. Renewable Energy",
				"7.3 Increase Energy Efficiency","7.3","Renewable Energy","7","7. Renewable Energy",
				"7.a Increase International Participation","7.a","Renewable Energy","7","7. Renewable Energy",
				"7.b Improve Renewable Energy Infrastructure","7.b","Renewable Energy","7","7. Renewable Energy",
				"8.1 Economic Growth","8.1","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.2 Economic Productivity","8.2","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.3 Economic Development Policies","8.3","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.4 Resource Efficiency","8.4","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.5 Full Employment","8.5","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.6 Increase Youth Employment","8.6","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.7 End Forced Labor","8.7","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.8 Protect Labor Rights","8.8","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.9 Grow Sustainable Tourism Industry","8.9","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.10 Access to Financial Services","8.10","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.a Support Trade","8.a","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"8.b Global Employment Strategies","8.b","Good Jobs & Econ Growth","8","8. Good Jobs & Econ Growth",
				"9.1 Equity & Affordability Infrastructure","9.1","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"9.2 Sustainable Industrialization","9.2","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"9.3 Financial Services for Small Businesses","9.3","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"9.4 Existing Infrastructure Upgrades","9.4","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"9.5 Scientific Research","9.5","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"9.a Financial & Technical Support","9.a","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"9.b Domestic Technology Development","9.b","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"9.c Access to Information & Communication","9.c","Innovation and Infrastructure","9","9. Innovation and Infrastructure",
				"10.1 Sustainable Income Growth","10.1","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.2 Social, Economic, & Political Inclusion","10.2","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.3 Equal Opportunity","10.3","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.4 Policies for Equality","10.4","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.5 Market Regulation","10.5","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.6 Political Representation","10.6","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.7 Safe & Orderly Migration ","10.7","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.a Elevate Developing Countries","10.a","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.b Foreign Development Assistance","10.b","Reduced Inequalities","10","10. Reduced Inequalities",
				"10.c Remittance Cost Reduction","10.c","Reduced Inequalities","10","10. Reduced Inequalities",
				"11.1 Access to Housing","11.1","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.2 Access to Transportation Systems","11.2","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.3 Sustainable & Inclusive  Urbanization","11.3","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.4 Culture & Heritage Protection","11.4","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.5 Resistance to Disaster","11.5","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.6 Reduce Environmental Impact","11.6","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.7 Access to Public & Green Space","11.7","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.a Connectivity Between Regions","11.a","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.b Policies for Sustainability & Inclusion","11.b","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"11.c Sustainable and Resilient Buildings","11.c","Sustainable Cities & Communities","11","11. Sustainable Cities & Communities",
				"12.1 Global Action Framework","12.1","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.2 Sustainable Resource Management","12.2","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.3 Reduce Food Waste","12.3","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.4 Waste Management","12.4","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.5 Waste Prevention & Reduction","12.5","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.6 Corporate Sustainability","12.6","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.7 Sustainable Procurement","12.7","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.8 Sustainable Lifestyle Education","12.8","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.a Sustainability Support for Developing Countries","12.a","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.b Impact Measurement","12.b","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"12.c Reduce Fossil Fuel Subsidies","12.c","Sustainable Consumption & Production","12","12. Sustainable Consumption & Production",
				"13.1 Climate Resilience","13.1","Climate Action","13","13. Climate Action",
				"13.2 Policies & Planning for Climate Change","13.2","Climate Action","13","13. Climate Action",
				"13.3 Climate Change Education","13.3","Climate Action","13","13. Climate Action",
				"13.a Implement UN Climate Change framework","13.a","Climate Action","13","13. Climate Action",
				"13.b Climate Change Management Capacity","13.b","Climate Action","13","13. Climate Action",
				"14.1 Marine Pollution Prevention & Reduction","14.1","Life Below Water","14","14. Life Below Water",
				"14.2 Coastal Ecosystem Management & Protection","14.2","Life Below Water","14","14. Life Below Water",
				"14.3 Minimize Ocean Acidification","14.3","Life Below Water","14","14. Life Below Water",
				"14.4 End Overfishing","14.4","Life Below Water","14","14. Life Below Water",
				"14.5 Coastal & Marine Conservation","14.5","Life Below Water","14","14. Life Below Water",
				"14.6 Eliminate Overfishing Subsidies","14.6","Life Below Water","14","14. Life Below Water",
				"14.7 Economic Benefits for Sustainable Marine Practices","14.7","Life Below Water","14","14. Life Below Water",
				"14.a Marine Research","14.a","Life Below Water","14","14. Life Below Water",
				"14.b Access to Marine Resources & Markets","14.b","Life Below Water","14","14. Life Below Water",
				"14.c Ocean Conservation","14.c","Life Below Water","14","14. Life Below Water",
				"15.1 Freshwater Ecosystem Conservation & Restoration","15.1","Life on Land","15","15. Life on Land",
				"15.2 Sustainable Forest Management","15.2","Life on Land","15","15. Life on Land",
				"15.3 Land Degredation Neutrality","15.3","Life on Land","15","15. Life on Land",
				"15.4 Moutain Ecosystem Conservation","15.4","Life on Land","15","15. Life on Land",
				"15.5 Reduce Loss of Biodiversity & Habitat","15.5","Life on Land","15","15. Life on Land",
				"15.6 Genetic Resource Sharing","15.6","Life on Land","15","15. Life on Land",
				"15.7 End Species Poaching & Trafficking","15.7","Life on Land","15","15. Life on Land",
				"15.8 Species Evasion Prevention & Reduction","15.8","Life on Land","15","15. Life on Land",
				"15.9 Ecosystem & Biodiversity Planning","15.9","Life on Land","15","15. Life on Land",
				"15.a Financial Resources for Conservation","15.a","Life on Land","15","15. Life on Land",
				"15.b Resources for Sustainable Forest Management","15.b","Life on Land","15","15. Life on Land",
				"15.c Global Support Species Protection","15.c","Life on Land","15","15. Life on Land",
				"16.1 Reduce Violence","16.1","Peace & Justice","16","16. Peace & Justice",
				"16.2 End Child Abuse, Exploitation, Trafficking, and Violence","16.2","Peace & Justice","16","16. Peace & Justice",
				"16.3 Justice Equality","16.3","Peace & Justice","16","16. Peace & Justice",
				"16.4 Reduce Organized Crime","16.4","Peace & Justice","16","16. Peace & Justice",
				"16.5 Reduce Corruption & Bribery","16.5","Peace & Justice","16","16. Peace & Justice",
				"16.6 Institutional Accountability & Transparency","16.6","Peace & Justice","16","16. Peace & Justice",
				"16.7 Participatory & Inclusive Governance","16.7","Peace & Justice","16","16. Peace & Justice",
				"16.8 Developing Countries Governmental Participation","16.8","Peace & Justice","16","16. Peace & Justice",
				"16.9 Legal Identities for All","16.9","Peace & Justice","16","16. Peace & Justice",
				"16.10 Equal Access to Information & Freedoms","16.10","Peace & Justice","16","16. Peace & Justice",
				"16.a Stronger Institutions for Violence Prevention","16.a","Peace & Justice","16","16. Peace & Justice",
				"16.b Non-Discriminatory Laws & Policies","16.b","Peace & Justice","16","16. Peace & Justice",
				"17.1 Domestic Resource Mobilization","17.1","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.2 Assitance to Developing Countries","17.2","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.3 Financial Resources for Developing Countries","17.3","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.4 Long-Term Debt Sustainability","17.4","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.5 Investment Promotion Regimes","17.5","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.6 Global Cooperation and Innovation","17.6","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.7 Sustainable Technology Development","17.7","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.8 Innovation Capacity Building","17.8","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.9 International Capacity Building","17.9","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.10 Universal Open Trade","17.10","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.11 Exports from Developing Countries","17.11","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.12 Duty-Free Market Access ","17.12","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.13 Global Macroeconomic Stability","17.13","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.14 Coherent Sustainable Development Policies","17.14","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.15 Respectful Policy Implementation","17.15","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.16 Global & Multi-Stakeholder Partnerships","17.16","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.17 Effective Multi-Sector Partnerships","17.17","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.18 Accessibility to Reliable Data","17.18","Partnerships for Goals","17","17. Partnerships for Goals",
				"17.19 Measure Progress","17.19","Partnerships for Goals","17","17. Partnerships for Goals"
		};
		
		for(int i = 0; i < values.length ; i+=5) {
			SDG value = new SDG();
			value.setShortName(values[i]);
			value.setSDGCode(values[i+1]);
			value.setGoal(values[i+2]);
			value.setGoalNumber(Long.parseLong(values[i+3]));
			value.setCompleteGoalName(values[i+4]);
			value.setCreatedAt(new Date());
			value.setUpdatedAt(new Date());
			sdgRepository.save(value);
		}
		return sdgRepository.count()+"";

	}


}
