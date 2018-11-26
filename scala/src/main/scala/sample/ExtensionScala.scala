package sample

import kr.bydelta.koala.ExtUtil
import kr.bydelta.koala.Implicits._

object ExtensionScala {
  def main(args: Array[String]): Unit = {
    /**** 한글 여부 확인 ****/
    println(s"'가'.isHangul = ${'가'.isHangul}") // true
    println(s"'ㄱ'.isHangul = ${'ㄱ'.isHangul}") // true
    println(s"'a'.isHangul = ${'a'.isHangul}") // false

    println("\"갤럭시S는\".isHangulEnding = " + "갤럭시S는".isHangulEnding) // true
    println("\"abc\".isHangulEnding = " + "abc".isHangulEnding) // false
    println("\"보기 ㄱ\".isHangulEnding = " + "보기 ㄱ".isHangulEnding) // true

    println(s"'가'.isCompleteHangul = ${'가'.isCompleteHangul}") // true
    println(s"'ㄱ'.isCompleteHangul = ${'ㄱ'.isCompleteHangul}") // false
    println(s"'a'.isCompleteHangul = ${'a'.isCompleteHangul}") // false

    /**** 종성으로 끝나는지 확인 ****/
    println(s"'각'.isJongsungEnding = ${'각'.isJongsungEnding}") // true
    println(s"'가'.isJongsungEnding = ${'가'.isJongsungEnding}") // false
    println(s"'m'.isJongsungEnding = ${'m'.isJongsungEnding}") // false

    /**** 초, 중, 종성 한글 자모 값으로 분해 ****/
    println(s"'가'.getChosung = ${'가'.getChosung}") // 'ㄱ'에 해당하는 초성 문자 \u1100
    println(s"'가'.getJungsung = ${'가'.getJungsung}") // 'ㅏ'에 해당하는 초성 문자 \u1161
    println(s"'가'.getJongsung = ${'가'.getJongsung}") // null
    println(s"'각'.getJongsung = ${'각'.getJongsung}") // 'ㄱ'에 해당하는 종성문자 \u11A8
    println(s"'ㄱ'.getChosung = ${'ㄱ'.getChosung}") // null
    println(s"'가'.dissembleHangul = ${'가'.dissembleHangul}") // Triple('\u1100', '\u1161', null)
    println(s"'각'.dissembleHangul = ${'각'.dissembleHangul}") // Triple('\u1100', '\u1161', '\u11A8')
    println("\"가각\".dissembleHangul = " + "가각".dissembleHangul) // "\u1100\u1161\u1100\u1161\u11A8" (문자 사이를 띄워서 표시하면: 'ᄀ ᅡ ᄀ ᅡ ᆨ')

    /**** 종성 한글 자모값으로 변환 ****/
    println(s"""ExtUtil.getChoToJong.get('ㄵ') = ${ExtUtil.getChoToJong.get('ㄵ')}""") // \u11AC

    /**** 한글 자모 범위에 속하는 초, 중, 종성인지 확인 ****/
    println(s"'ㄱ'.isChosungJamo = ${'ㄱ'.isChosungJamo}") // false
    println(s"'\u1100'.isChosungJamo = ${'\u1100'.isChosungJamo}") // true
    println(s"'ㅏ'.isJungsungJamo = ${'ㅏ'.isJungsungJamo}") // false
    println(s"'\u1161'.isJungsungJamo = ${'\u1161'.isJungsungJamo}") // true
    println(s"'ㄱ'.isJongsungJamo = ${'ㄱ'.isJongsungJamo}") // false
    println(s"'\u11A8'.isJongsungJamo = ${'\u11A8'.isJongsungJamo}") // true

    /**** 한글 자모 결합 ****/
    println(s"('\u1100', '\u1161', Option.empty[Char]).assembleHangul = ${('\u1100', '\u1161', Option.empty[Char]).assembleHangul}") // '가'
    println(s"('\u1100', '\u1161', Option('\u11AB')).assembleHangul = ${('\u1100', '\u1161', Option('\u11AB')).assembleHangul}") // '간'
    println("\"\u1100\u1161\u1102\u1161\u11ab\".assembleHangul = " + "\u1100\u1161\u1102\u1161\u11ab".assembleHangul) // "가난"
    println(s"ExtUtil.assembleHangul(null, '\u1161') = ${ExtUtil.assembleHangul(null, '\u1161')}") // '아'
    println(s"ExtUtil.assembleHangul('\u1100', null, '\u11A8') = ${ExtUtil.assembleHangul('\u1100', null, '\u11A8')}") // '극'


    println("\"ABCD\".alphaToHangul = " + "ABCD".alphaToHangul) // "에이비씨디"
    println("\"갤럭시S\".alphaToHangul = " + "갤럭시S".alphaToHangul) // "갤럭시에스"
    println("\"cup\".alphaToHangul = " + "cup".alphaToHangul) // "씨유피"

    println("\"에스비에스\".isAlphaPronounced = " + "에스비에스".isAlphaPronounced) // true
    println("\"갤럭시에스\".isAlphaPronounced = " + "갤럭시에스".isAlphaPronounced) // false

    println("\"갤럭시에스\".hangulToAlpha = " + "갤럭시에스".hangulToAlpha) // "갤럭시S"
    println("\"에이디에이치디\".hangulToAlpha = " + "에이디에이치디".hangulToAlpha) // "ADHD"

    println(s"'國'.isHanja = ${'國'.isHanja}") // true
    println(s"'國'.isCJKHanja = ${'國'.isCJKHanja}") // true

    println("\"國篇\".hanjaToHangul = " + "國篇".hanjaToHangul(true)) // "국편"
    println("\"國篇은 오늘\".hanjaToHangul = " + "國篇은 오늘".hanjaToHangul(true)) // "국편은 오늘"
    println("\"300 兩의 돈\".hanjaToHangul = " + "300 兩의 돈".hanjaToHangul(true)) // "300 냥의 돈"
    println("\"樂園\".hanjaToHangul = " + "樂園".hanjaToHangul(true)) // "낙원" (두음법칙)
    println("\"樂園\".hanjaToHangul = " + "樂園".hanjaToHangul(true)) // "낙원" (두음법칙)

      val x: Option[Char] = Option.apply(ExtUtil.getJongsung('가'))
        println(x == None)
  }
}
