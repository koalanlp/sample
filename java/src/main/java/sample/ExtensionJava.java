package sample;

import kr.bydelta.koala.ExtUtil;

public class ExtensionJava {
    public static void main(String[] args) {
        /**** 한글 여부 확인 ****/
        System.out.println("ExtUtil.isHangul('가') = " + ExtUtil.isHangul('가')); //  true
        System.out.println("ExtUtil.isHangul('ㄱ') = " + ExtUtil.isHangul('ㄱ')); //  true
        System.out.println("ExtUtil.isHangul('a') = " + ExtUtil.isHangul('a')); //  false

        System.out.println("ExtUtil.isHangulEnding(\"갤럭시S는\") = " + ExtUtil.isHangulEnding("갤럭시S는")); //  true
        System.out.println("ExtUtil.isHangulEnding(\"abc\") = " + ExtUtil.isHangulEnding("abc")); //  false
        System.out.println("ExtUtil.isHangulEnding(\"보기 ㄱ\") = " + ExtUtil.isHangulEnding("보기 ㄱ")); //  true

        System.out.println("ExtUtil.isCompleteHangul('가') = " + ExtUtil.isCompleteHangul('가')); //  true
        System.out.println("ExtUtil.isCompleteHangul('ㄱ') = " + ExtUtil.isCompleteHangul('ㄱ')); //  false
        System.out.println("ExtUtil.isCompleteHangul('a') = " + ExtUtil.isCompleteHangul('a')); //  false

        /**** 종성으로 끝나는지 확인 ****/
        System.out.println("ExtUtil.isJongsungEnding('각') = " + ExtUtil.isJongsungEnding('각')); //  true
        System.out.println("ExtUtil.isJongsungEnding('가') = " + ExtUtil.isJongsungEnding('가')); //  false
        System.out.println("ExtUtil.isJongsungEnding('m') = " + ExtUtil.isJongsungEnding('m')); //  false

        /**** 초, 중, 종성 한글 자모 값으로 분해 ****/
        System.out.println("ExtUtil.getChosung('가') = " + ExtUtil.getChosung('가')); //  'ㄱ'에 해당하는 초성 문자 \u1100
        System.out.println("ExtUtil.getJungsung('가') = " + ExtUtil.getJungsung('가')); //  'ㅏ'에 해당하는 초성 문자 \u1161
        System.out.println("ExtUtil.getJongsung('가') = " + ExtUtil.getJongsung('가')); //  null
        System.out.println("ExtUtil.getJongsung('각') = " + ExtUtil.getJongsung('각')); //  'ㄱ'에 해당하는 종성문자 \u11A8
        System.out.println("ExtUtil.getChosung('ㄱ') = " + ExtUtil.getChosung('ㄱ')); //  null
        System.out.println("ExtUtil.dissembleHangul('가') = " + ExtUtil.dissembleHangul('가')); //  kotlin.Triple('\u1100', '\u1161', null)
        System.out.println("ExtUtil.dissembleHangul('각') = " + ExtUtil.dissembleHangul('각')); //  kotlin.Triple('\u1100', '\u1161', '\u11A8')
        System.out.println("ExtUtil.dissembleHangul(\"가각\") = " + ExtUtil.dissembleHangul("가각")); //  "\u1100\u1161\u1100\u1161\u11A8" (문자 사이를 띄워서 표시하면: 'ᄀ ᅡ ᄀ ᅡ ᆨ')

        /**** 종성 한글 자모값으로 변환 ****/
        System.out.println("ExtUtil.getChoToJong().get('ㄵ') = " + ExtUtil.getChoToJong().get('ㄵ')); //  \u11AC

        /**** 한글 자모 범위에 속하는 초, 중, 종성인지 확인 ****/
        System.out.println("ExtUtil.isChosungJamo('ㄱ') = " + ExtUtil.isChosungJamo('ㄱ')); //  false
        System.out.println("ExtUtil.isChosungJamo('\u1100') = " + ExtUtil.isChosungJamo('\u1100')); //  true
        System.out.println("ExtUtil.isJungsungJamo('ㅏ') = " + ExtUtil.isJungsungJamo('ㅏ')); //  false
        System.out.println("ExtUtil.isJungsungJamo('\u1161') = " + ExtUtil.isJungsungJamo('\u1161')); //  true
        System.out.println("ExtUtil.isJongsungJamo('ㄱ') = " + ExtUtil.isJongsungJamo('ㄱ')); //  false
        System.out.println("ExtUtil.isJongsungJamo('\u11A8') = " + ExtUtil.isJongsungJamo('\u11A8')); //  true

        /**** 한글 자모 결합 ****/
        System.out.println("ExtUtil.assembleHangul(new kotlin.Triple<Character, Character, Character>('\u1100', '\u1161', null)) = " + ExtUtil.assembleHangul(new kotlin.Triple<Character, Character, Character>('\u1100', '\u1161', null))); //  '가'
        System.out.println("ExtUtil.assembleHangul(new kotlin.Triple('\u1100', '\u1161', '\u11AB')) = " + ExtUtil.assembleHangul(new kotlin.Triple<Character, Character, Character>('\u1100', '\u1161', '\u11AB'))); //  '간'
        System.out.println("ExtUtil.assembleHangul(\"\u1100\u1161\u1102\u1161\u11ab\") = " + ExtUtil.assembleHangul("\u1100\u1161\u1102\u1161\u11ab")); //  "가난"
        System.out.println("ExtUtil.assembleHangul(null, '\u1161') = " + ExtUtil.assembleHangul(null, '\u1161')); //  '아'
        System.out.println("ExtUtil.assembleHangul('\u1100', null, '\u11A8') = " + ExtUtil.assembleHangul('\u1100', null, '\u11A8')); //  '극'


        System.out.println("ExtUtil.alphaToHangul(\"ABCD\") = " + ExtUtil.alphaToHangul("ABCD")); //  "에이비씨디"
        System.out.println("ExtUtil.alphaToHangul(\"갤럭시S\") = " + ExtUtil.alphaToHangul("갤럭시S")); //  "갤럭시에스"
        System.out.println("ExtUtil.alphaToHangul(\"cup\") = " + ExtUtil.alphaToHangul("cup")); //  "씨유피"

        System.out.println("ExtUtil.isAlphaPronounced(\"에스비에스\") = " + ExtUtil.isAlphaPronounced("에스비에스")); //  true
        System.out.println("ExtUtil.isAlphaPronounced(\"갤럭시에스\") = " + ExtUtil.isAlphaPronounced("갤럭시에스")); //  false

        System.out.println("ExtUtil.hangulToAlpha(\"갤럭시에스\") = " + ExtUtil.hangulToAlpha("갤럭시에스")); //  "갤럭시S"
        System.out.println("ExtUtil.hangulToAlpha(\"에이디에이치디\") = " + ExtUtil.hangulToAlpha("에이디에이치디")); //  "ADHD"

        System.out.println("ExtUtil.isHanja('國') = " + ExtUtil.isHanja('國')); //  true
        System.out.println("ExtUtil.isCJKHanja('國') = " + ExtUtil.isCJKHanja('國')); //  true

        System.out.println("ExtUtil.hanjaToHangul(\"國篇\") = " + ExtUtil.hanjaToHangul("國篇")); //  "국편"
        System.out.println("ExtUtil.hanjaToHangul(\"國篇은 오늘\") = " + ExtUtil.hanjaToHangul("國篇은 오늘")); //  "국편은 오늘"
        System.out.println("ExtUtil.hanjaToHangul(\"300 兩의 돈\") = " + ExtUtil.hanjaToHangul("300 兩의 돈")); //  "300 냥의 돈"
        System.out.println("ExtUtil.hanjaToHangul(\"樂園\") = " + ExtUtil.hanjaToHangul("樂園")); //  "낙원" (두음법칙)
    }
}
