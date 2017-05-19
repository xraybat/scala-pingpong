/**
  * Created by psc on 28/04/17.
  */

package au.com.carringbushsw.PingPong

import akka.actor.{ Actor, ActorSystem, Props }

abstract class Game {
  def ^(): Unit  // `play` message operator

  // optional word aliases for operators
  def play(): Unit = this.^()
}

////////////////////////////////////////

// game controller
class PingPong extends Game {
  override def ^(): Unit = {
    /**
      * note: actors must persist in *own* thread
      */
    /*val system = ActorSystem("PingPongSystem")
    val pingActor = system.actorOf(Props(new Pong("pongbat1")), name = "pingactor")
    val pongActor = system.actorOf(Props(new Pong("pongbat1")), name = "pongactor")
    val ballActor = system.actorOf(Props(new Pung("pungball1")), name = "pungactor")
    */
    /*val batPing = new Ping("pingbat1")     // or??...
    val batPong = new Pong("pongbat1")
    val ballPung = new Pung("pungball1")*/
    object batPing extends Ping("pingbat1")  // ...this??
    object batPong extends Pong("pongbat1")
    object ballPung extends Pung("pungball1")

    println("actors...")
    println(batPing)
    println(batPong)
    println(ballPung)

    println("playing...")
    batPing ! (ballPung, batPong)
    // or: batPing hit (ballPung, batPong)

  } // ^
} // PingPong