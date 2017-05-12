/**
  * Created by psc on 28/04/17.
  */

package au.om.carringbushsw.PingPong

import org.scalatest.FlatSpec

class PingPongSpec extends FlatSpec {
  object batPing extends Ping("pingbat1")
  object batPong extends Pong("pongbat1")
  object ballPung extends Pung("pungball1")
  private val gamePingPong = new PingPong()

  "a Ping bat" should "have a unique uuid" in {
    println(batPing)
    assert(batPing.toString() === batPing.toString())
    assert(batPing.toString() !== batPong.toString())
    assert(batPing.toString() !== ballPung.toString())
  }

  "a Pong bat" should "have a unique uuid" in {
    println(batPong)
    assert(batPong.toString() === batPong.toString())
    assert(batPong.toString() !== batPing.toString())
    assert(batPong.toString() !== ballPung.toString())
  }

  "a Pung ball" should "have a unique uuid" in {
    println(ballPung)
    assert(ballPung.toString() === ballPung.toString())
    assert(ballPung.toString() !== batPing.toString())
    assert(ballPung.toString() !== batPong.toString())
  }

  "a PingPong game" should "play^ (with operators)" in {
    gamePingPong.^
    assert(true)
  }

  "a PingPong game" should "play (with words)" in {
    gamePingPong.play
    assert(true)
  }

  /*it should "return 'done!' if I call sayThis('done!')" in {
    assert(new HelloWorld().sayThis("done!") === "done!")
  }*/
} // PingPongSpec