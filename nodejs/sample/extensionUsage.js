const {initialize} = require("koalanlp/Util");
const ExtUtil = require("koalanlp/ExtUtil");

initialize({packages: {CORE: 'LATEST'}})
    .then(() => {
        /**** 한글 여부 확인 ****/
        console.log(`ExtUtil.isHangul('가') = ${ExtUtil.isHangul('가')}`);	// [true]
        console.log(`ExtUtil.isHangul('ㄱ') = ${ExtUtil.isHangul('ㄱ')}`);	// [true]
        console.log(`ExtUtil.isHangul('a') = ${ExtUtil.isHangul('a')}`);	// [false]

        console.log(`ExtUtil.isHangulEnding("갤럭시S는") = ${ExtUtil.isHangulEnding("갤럭시S는")}`);	// true
        console.log(`ExtUtil.isHangulEnding("abc") = ${ExtUtil.isHangulEnding("abc")}`);	// false
        console.log(`ExtUtil.isHangulEnding("보기 ㄱ") = ${ExtUtil.isHangulEnding("보기 ㄱ")}`);	// true

        console.log(`ExtUtil.isCompleteHangul('가') = ${ExtUtil.isCompleteHangul('가')}`);	// [true]
        console.log(`ExtUtil.isCompleteHangul('ㄱ') = ${ExtUtil.isCompleteHangul('ㄱ')}`);	// [false]
        console.log(`ExtUtil.isCompleteHangul('a') = ${ExtUtil.isCompleteHangul('a')}`);	// [false]

        /**** 종성으로 끝나는지 확인 ****/
        console.log(`ExtUtil.isJongsungEnding('각') = ${ExtUtil.isJongsungEnding('각')}`);	// true
        console.log(`ExtUtil.isJongsungEnding('가') = ${ExtUtil.isJongsungEnding('가')}`);	// false
        console.log(`ExtUtil.isJongsungEnding('m') = ${ExtUtil.isJongsungEnding('m')}`);	// false

        /**** 초, 중, 종성 한글 자모 값으로 분해 ****/
        console.log(`ExtUtil.getChosung('가') = ${ExtUtil.getChosung('가')}`);	// 'ㄱ'에 해당하는 초성 문자 [\u1100]
        console.log(`ExtUtil.getJungsung('가') = ${ExtUtil.getJungsung('가')}`);	// 'ㅏ'에 해당하는 초성 문자 [\u1161]
        console.log(`ExtUtil.getJongsung('가') = ${ExtUtil.getJongsung('가')}`);	// [undefined]
        console.log(`ExtUtil.getJongsung('각') = ${ExtUtil.getJongsung('각')}`);	// 'ㄱ'에 해당하는 종성문자 [\u11A8]
        console.log(`ExtUtil.getChosung('ㄱ') = ${ExtUtil.getChosung('ㄱ')}`);	// [undefined]
        console.log(`ExtUtil.dissembleHangul('가') = ${ExtUtil.dissembleHangul('가')}`);	// '\u1100\u1161'
        console.log(`ExtUtil.dissembleHangul('각') = ${ExtUtil.dissembleHangul('각')}`);	// '\u1100\u1161\u11A8'
        console.log(`ExtUtil.dissembleHangul("가각") = ${ExtUtil.dissembleHangul("가각")}`);	// "\u1100\u1161\u1100\u1161\u11A8" (문자 사이를 띄워서 표시하면: 'ᄀ ᅡ ᄀ ᅡ ᆨ')

        /**** 종성 한글 자모값으로 변환 ****/
        console.log(`ExtUtil.ChoToJong.get('ㄵ') = ${ExtUtil.ChoToJong.get('ㄵ')}`);	// \u11AC

        /**** 한글 자모 범위에 속하는 초, 중, 종성인지 확인 ****/
        console.log(`ExtUtil.isChosungJamo('ㄱ') = ${ExtUtil.isChosungJamo('ㄱ')}`);	// [false]
        console.log(`ExtUtil.isChosungJamo('\u1100') = ${ExtUtil.isChosungJamo('\u1100')}`);	// [true]
        console.log(`ExtUtil.isJungsungJamo('ㅏ') = ${ExtUtil.isJungsungJamo('ㅏ')}`);	// [false]
        console.log(`ExtUtil.isJungsungJamo('\u1161') = ${ExtUtil.isJungsungJamo('\u1161')}`);	// [true]
        console.log(`ExtUtil.isJongsungJamo('ㄱ') = ${ExtUtil.isJongsungJamo('ㄱ')}`);	// [false]
        console.log(`ExtUtil.isJongsungJamo('\u11A8') = ${ExtUtil.isJongsungJamo('\u11A8')}`);	// [true]

        /**** 한글 자모 결합 ****/
        console.log(`ExtUtil.assembleHangul("\u1100\u1161\u1102\u1161\u11ab") = ${ExtUtil.assembleHangul("\u1100\u1161\u1102\u1161\u11ab")}`);	// "가난"
        console.log(`ExtUtil.assembleHangulTriple(undefined, '\u1161') = ${ExtUtil.assembleHangulTriple(undefined, '\u1161')}`);	// '아'
        console.log(`ExtUtil.assembleHangulTriple('\u1100', undefined, '\u11A8') = ${ExtUtil.assembleHangulTriple('\u1100', undefined, '\u11A8')}`);	// '극'
    }).catch((err) => console.error('Error occurred!', err));