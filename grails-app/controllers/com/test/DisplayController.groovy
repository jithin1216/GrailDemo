package com.test
import grails.converters.XML
import grails.plugins.rest.client.RestBuilder
import groovy.util.XmlSlurper
import wslite.soap.*
class DisplayController {
	
	static defaultAction="initialize"
	def initialize(){
		render view:"/display/test";
	}
	
	def show(){
		System.out.println("---------------")
		def url = "https://www.q88.com/WS/Q88WSInternal.asmx"
		def soapClient = new SOAPClient(url)
		def message = new SOAPMessageBuilder().build({
			body {
				ConvertTemperature(xmlns: "http://q88.com/webservices/") {
					property("Celsius")
					val("20")
				}
			}  
		})
		def response = soapClient.send(message.toString());   
		def input = response.ConvertTemperatureResponse.ConvertTemperatureResult.Celsius 
		def output = response.ConvertTemperatureResponse.ConvertTemperatureResult.Fahrenheit
		System.out.println(input) 
		System.out.println(output)
		//RestBuilder restBuilder  = new RestBuilder()
		//String url = "http://www.q88.com/WS/q88wsinternal.asmx/ConvertTemperature?property=Celsius&val=23"
		//def resp = restBuilder.get(url)
		
		//def list=new XmlSlurper().parseText(resp.xml)
		//System.out.println(resp.xml) 
		//def articleXML = new ArticleXML(resp.xml)  
	}

    def index() { 
		def resp = new RestBuilder()   
	}
}
