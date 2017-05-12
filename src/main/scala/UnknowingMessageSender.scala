/**
  * Created by psc on 28/04/17.
  */

import akka.actor.{ Actor, ActorSystem, Props }

/* extends Actor */
class UnknowingMessageSender(name: String) {
  private val uuid: String = java.util.UUID.randomUUID.toString

  /*var msg: Object
  var recipient: Object

  def giveMessage(msg: Object, recipient: Object) : Unit = { this.msg = msg; this.recipient = recipient }
  def deliverMessage() : Unit = { /*recipient ! msg*/ }
  def stateChanges() : State { asleep -> givenMessage -> travelling -> deliveredMessage -> asleep }
*/
} // UnknowingMessageSender
