package controllers

import play.api.mvc._
import play.api.libs.json._
import $package$.BuildInfo
import com.typesafe.config.ConfigRenderOptions
import play.api.Configuration

class Application(configuration : Configuration) extends Controller {

  def index = Action {
    Ok("Hello")
  }

  def status = Action {

    val version = Json.obj("buildVersion" -> BuildInfo.version)
    val config = Json.obj("configuration" -> Json.parse(configuration.underlying.root().render(ConfigRenderOptions.concise())))
    Ok(version ++ config)
  }
}
