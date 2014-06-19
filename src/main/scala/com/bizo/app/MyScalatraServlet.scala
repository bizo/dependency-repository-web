package com.bizo.app

import org.scalatra._
import scalate.ScalateSupport

class MyScalatraServlet extends TemplateAppStack {

  get("/") {
    contentType = "text/html"
    
    scaml("/index")
  }
}
