package sample

import kr.bydelta.koala.*

fun main(args: Array<String>) {
    /**** 한글 여부 확인 ****/
    println("'가'.isHangul() = ${'가'.isHangul()}") // true
    println("'ㄱ'.isHangul() = ${'ㄱ'.isHangul()}") // true
    println("'a'.isHangul() = ${'a'.isHangul()}") // false

    println("\"갤럭시S는\".isHangulEnding() = ${"갤럭시S는".isHangulEnding()}") // true
    println("\"abc\".isHangulEnding() = ${"abc".isHangulEnding()}") // false
    println("\"보기 ㄱ\".isHangulEnding() = ${"보기 ㄱ".isHangulEnding()}") // true

    println("'가'.isCompleteHangul() = ${'가'.isCompleteHangul()}") // true
    println("'ㄱ'.isCompleteHangul() = ${'ㄱ'.isCompleteHangul()}") // false
    println("'a'.isCompleteHangul() = ${'a'.isCompleteHangul()}") // false

    /**** 종성으로 끝나는지 확인 ****/
    println("'각'.isJongsungEnding() = ${'각'.isJongsungEnding()}") // true
    println("'가'.isJongsungEnding() = ${'가'.isJongsungEnding()}") // false
    println("'m'.isJongsungEnding() = ${'m'.isJongsungEnding()}") // false

    /**** 초, 중, 종성 한글 자모 값으로 분해 ****/
    println("'가'.getChosung() = ${'가'.getChosung()}") // 'ㄱ'에 해당하는 초성 문자 \u1100
    println("'가'.getJungsung() = ${'가'.getJungsung()}") // 'ㅏ'에 해당하는 초성 문자 \u1161
    println("'가'.getJongsung() = ${'가'.getJongsung()}") // null
    println("'각'.getJongsung() = ${'각'.getJongsung()}") // 'ㄱ'에 해당하는 종성문자 \u11A8
    println("'ㄱ'.getChosung() = ${'ㄱ'.getChosung()}") // null
    println("'가'.dissembleHangul() = ${'가'.dissembleHangul()}") // Triple('\u1100', '\u1161', null)
    println("'각'.dissembleHangul() = ${'각'.dissembleHangul()}") // Triple('\u1100', '\u1161', '\u11A8')
    println("\"가각\".dissembleHangul() = ${"가각".dissembleHangul()}") // "\u1100\u1161\u1100\u1161\u11A8" (문자 사이를 띄워서 표시하면: 'ᄀ ᅡ ᄀ ᅡ ᆨ')

    /**** 종성 한글 자모값으로 변환 ****/
    println("""ChoToJong['ㄵ'] = ${ChoToJong['ㄵ']}""") // \u11AC

    /**** 한글 자모 범위에 속하는 초, 중, 종성인지 확인 ****/
    println("'ㄱ'.isChosungJamo() = ${'ㄱ'.isChosungJamo()}") // false
    println("'\u1100'.isChosungJamo() = ${'\u1100'.isChosungJamo()}") // true
    println("'ㅏ'.isJungsungJamo() = ${'ㅏ'.isJungsungJamo()}") // false
    println("'\u1161'.isJungsungJamo() = ${'\u1161'.isJungsungJamo()}") // true
    println("'ㄱ'.isJongsungJamo() = ${'ㄱ'.isJongsungJamo()}") // false
    println("'\u11A8'.isJongsungJamo() = ${'\u11A8'.isJongsungJamo()}") // true

    /**** 한글 자모 결합 ****/
    println("Triple('\u1100', '\u1161', null as Char?).assembleHangul() = ${Triple('\u1100', '\u1161', null as Char?).assembleHangul()}") // '가'
    println("Triple('\u1100', '\u1161', '\u11AB').assembleHangul() = ${Triple('\u1100', '\u1161', '\u11AB').assembleHangul()}") // '간'
    println("\"\u1100\u1161\u1102\u1161\u11ab\".assembleHangul() = ${"\u1100\u1161\u1102\u1161\u11ab".assembleHangul()}") // "가난"
    println("assembleHangul(jung = '\u1161') = ${assembleHangul(jung = '\u1161')}") // '아'
    println("assembleHangul(cho = '\u1100', jong = '\u11A8') = ${assembleHangul(cho = '\u1100', jong = '\u11A8')}") // '극'


    println("\"ABCD\".alphaToHangul() = ${"ABCD".alphaToHangul()}") // "에이비씨디"
    println("\"갤럭시S\".alphaToHangul() = ${"갤럭시S".alphaToHangul()}") // "갤럭시에스"
    println("\"cup\".alphaToHangul() = ${"cup".alphaToHangul()}") // "씨유피"

    println("\"에스비에스\".isAlphaPronounced() = ${"에스비에스".isAlphaPronounced()}") // true
    println("\"갤럭시에스\".isAlphaPronounced() = ${"갤럭시에스".isAlphaPronounced()}") // false

    println("\"갤럭시에스\".hangulToAlpha() = ${"갤럭시에스".hangulToAlpha()}") // "갤럭시S"
    println("\"에이디에이치디\".hangulToAlpha() = ${"에이디에이치디".hangulToAlpha()}") // "ADHD"

    println("'國'.isHanja() = ${'國'.isHanja()}") // true
    println("'國'.isCJKHanja() = ${'國'.isCJKHanja()}") // true

    println("\"國篇\".hanjaToHangul() = ${"國篇".hanjaToHangul()}") // "국편"
    println("\"國篇은 오늘\".hanjaToHangul() = ${"國篇은 오늘".hanjaToHangul()}") // "국편은 오늘"
    println("\"300 兩의 돈\".hanjaToHangul() = ${"300 兩의 돈".hanjaToHangul()}") // "300 냥의 돈"
    println("\"樂園\".hanjaToHangul() = ${"樂園".hanjaToHangul()}") // "낙원" (두음법칙)
    println("\"樂園\".hanjaToHangul() = ${"樂園".hanjaToHangul()}") // "낙원" (두음법칙)
}