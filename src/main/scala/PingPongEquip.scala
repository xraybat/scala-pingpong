/**
  * Created by psc on 28/04/17.
  */
/**
  * want to be able to send messages of form `Object ! Msg`
  * where '!', send-message operator, is replaced by 'hit' initially
  * as `Ping hit Ball` and `Pong hit Ball`
  *
  * like `Ping send-message(hit) Ball`
  *
  * so, methods / operators needed:
  *
  *   hit: `!`
  *   miss: `~`
  *   receiver: `<`
  *   play: `^`
  *
  * now: `batPing hit (ballPung, batPong)`
  * want: `batPing hit ballPung at batPong`
  */

package au.com.carringbushsw.PingPong

import akka.actor.{ Actor, ActorSystem, Props }
import au.com.carringbushsw.Tag._

/**
  *  sample actor:
  *
class HelloActor(myName: String) extends Actor {
  def receive = {
    case "hello"       => println("hello back at you, %s".format(myName))
    case "buenos dias" => println("buenos dias, %s!".format(myName))
    case _             => println("huh, %s?".format(myName))

  } // receive
} // HelloActor
  */

////////////////////////////////////////

trait Equip {
  val genus: String
  val name: String

  private val uuid = new TagUuid(EquipTag.Tag)

  override def toString: String = name + "/" + genus + ":" + uuid
}

// constants ///////////////////////////

object EquipTag {
  val Tag = "equiptag"
}

object EquipGenus {
  val Bat = "bat"
  val Ball = "ball"
}

////////////////////////////////////////

abstract class Bat(_name: String) extends Equip {
  val genus = EquipGenus.Bat
  val name = _name

  def !(ball: Ball, batTo: Bat) : Unit        // `hit` message operator
  def ~(ball: Ball, batThis: Bat) : Unit = {} // `miss`` message operator; implemented

  // optional word aliases for operators
  def hit(ball: Ball, batTo: Bat) : Unit = this ! (ball, batTo)
  def miss(ball: Ball, batThis: Bat) : Unit = this ~ (ball, batThis)
}

abstract class Ball(_name: String) extends Equip {
  val genus = EquipGenus.Ball
  val name = _name

  def <(batFrom: Bat, batTo: Option[Bat], action: (Ball, Bat) => Unit) : Unit // `receiver` message operator + function arg

  // optional word aliases for operators
  def receiver(batFrom: Bat, batTo: Option[Bat], action: (Ball, Bat) => Unit) : Unit = this < (batFrom, batTo, action)
}

abstract class PingPongBat(name: String) extends Bat(name) {}
abstract class PingPongBall(name: String) extends Ball(name) {}

////////////////////////////////////////

/**
  * these classes separated from above as i want them to be objects?? actors?? singletons??
  * or just change `val pingActor = new Ping("pingbat1")` to `new PingPongBat("pingbat1")`??
  */
/**
  * note: actors must persist in *own* thread
  */
/* extends Actor */
class Ping(name: String) extends PingPongBat(name) {
  override def !(ball: Ball, batTo: Bat) : Unit = ball < (this, Some(batTo), batTo.!)
}

/* extends Actor */
class Pong(name: String) extends PingPongBat(name) {
  override def !(ball: Ball, batTo: Bat) : Unit = ball < (this, None, batTo.~)
}

/* extends Actor */
class Pung(name: String) extends PingPongBall(name) {
  // either `batTo.!` (hit) or `batFrom.~` (miss) depending on `action` and `this`
  override def <(batFrom: Bat, batTo: Option[Bat] = None, action: (Ball, Bat) => Unit) : Unit = {
    println(">>> " + batTo)
    println("+++ " + batTo.getOrElse("(not given)"))
    assert(action != null)  // need this?? or is there a "must supply" as the reverse of `Option[]`??
    action(this, batFrom)   // operator form??
  }
}