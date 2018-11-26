
실제 사용 성능을 보여드리기 위해서, 카카오 뉴스에서 임의로 뉴스를 선택하고 10개 문단을 표본추출하였습니다.

실험환경: (Travis CI) **3GB** MaxHeap, **8**-core(s), Scala **2.12**

실험일시: Mon Nov 26 18:41:43 KST 2018

## 시간 성능 개관

. | KKMA | Twitter | Eunjeon | KOMORAN | Rhino | Arirang | Hannanum | Daon | ETRI
--- | --- | --- | --- | --- | --- | --- | --- | --- | ---
Initializing | 0.018s | 0.001s | 0.0s | 0.001s | 0.0s | 0.003s | 0.001s | 0.894s | 0.0s
Sentence #0 + __Lazy Loading__ | 2.21s | 1.039s | 4.758s | 1.064s | 0.396s | 0.207s | 0.28s | 0.011s | 12.911s
Sentence #1 | 0.087s | 0.032s | 0.008s | 0.009s | 0.052s | 0.022s | 0.014s | 0.002s | 0.145s
Sentence #2 | 0.048s | 0.014s | 0.004s | 0.003s | 0.041s | 0.011s | 0.008s | 0.001s | 0.101s
Sentence #3 | 0.06s | 0.014s | 0.004s | 0.003s | 0.04s | 0.009s | 0.009s | 0.001s | 0.086s
Sentence #4 | 0.091s | 0.023s | 0.008s | 0.005s | 0.058s | 0.015s | 0.01s | 0.001s | 0.131s
Sentence #5 | 0.036s | 0.008s | 0.004s | 0.002s | 0.026s | 0.007s | 0.005s | 0.001s | 0.078s
Sentence #6 | 0.037s | 0.012s | 0.003s | 0.002s | 0.022s | 0.009s | 0.004s | 0.001s | 0.081s
Sentence #7 | 0.052s | 0.014s | 0.004s | 0.003s | 0.037s | 0.01s | 0.006s | 0.001s | 0.094s
Sentence #8 | 0.032s | 0.011s | 0.003s | 0.001s | 0.03s | 0.01s | 0.005s | 0.0s | 0.071s
Sentence #9 | 0.03s | 0.008s | 0.002s | 0.001s | 0.014s | 0.01s | 0.003s | 0.001s | 0.07s

가장 __빠르게 초기화된__ 것은 `Eunjeon`이며, 가장 느리게 초기화된 패키지는 `Daon`입니다.

가장 __빠르게 첫 분석을 시작한__ 것은 `Arirang`이며, 가장 느리게 첫 분석을 시작한 패키지는 `ETRI`입니다.

첫 문장을 빼면, 평균적으로 __가장 빠르게 분석한__ 것은 `Daon`이며, 가장 느리게 분석한 패키지는 `ETRI`입니다.

## Tagging 결과

이제부터, 각 문장별로 품사분석 결과를 보여드립니다. 정답이 없으므로, 바르게 분석했는지의 여부는 여러분께서 판단하셔야 합니다. 다음과 같은 기준으로 평가해보시는 것을 권장합니다.

* 띄어쓰기나 어절구분은 정확한가? (바르게 분석했다면, 읽는 데 __어색함이 없어야__ 합니다)
* 고유명사나 신조어를 어떻게 분석했는가? (바르게 분석했다면, __고유명사/NNP__ 품사가 붙고, 적절히 띄어쓰기 되어야 합니다)
* 체언(명사)이 바르게 분석되었는가? (바르게 분석했다면, __N으로 시작하는 품사__ 가 붙어야 합니다)
* 용언(동사, 형용사)가 바르게 분석되었는가? (바르게 분석했다면, __V으로 시작하는 품사__ 가 붙고, 이후에, 어미(__E로 시작하는 품사__) 가 붙어야 합니다)   * 명사로도 쓰이는 동사는 N(명사)+XSV(용언화 접미사) 형태를 띄기도 합니다.
### 문장 번호 #0

원본 문장:
> 조강특위 위원장인 김용태 사무총장은 “2016년 총선 참패에 책임이 있는 이른바 진박 의원과 박근혜 전 대통령 탄핵을 가져온 최순실 국정농단 사태 연루자를 먼저 청산해야 하는 것 아니냐는 외부 위원들 의견이 강하다”고 말했다. 친박과 비박의 한판 승부가 불가피하다는 의미를 내포한다. 조강특위는 이와 별도로 7가지 원칙을 밝혔다. 첫째, 대여 투쟁에 미온적인 인사 둘째, 반(反)시장적 입법 참여 인사 셋째, 자유민주주의와 안보의식이 미진한 인사 넷째, 2016년 총선 ‘진박 공천’ 연루 인사 다섯째, 최순실 국정농단 사태 관련 인사 여섯째, 당 분열 조장 인사 일곱째, 존재감이 미약한 영남 다선 인사 등을 인적 쇄신 대상으로 삼겠다는 것이다.
#### 어절 구분
##### KKMA
> 조강⎵특위⎵위원장인⎵김⎵용태⎵사무총장은⎵“2016⎵년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근⎵혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순⎵실⎵국정⎵농단⎵사태⎵연루자를⎵먼저⎵청산해야⎵하는⎵것⎵아니냐
>
> 는⎵외부⎵위원들⎵의견이⎵강하다⎵”고⎵말했다.
>
> 친⎵박과⎵비⎵박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다.
>
> 조강⎵특위는⎵이와⎵별도로⎵7가지⎵원칙을⎵밝혔다.
>
> 첫째,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째,⎵반(⎵反)⎵시장적⎵입법⎵참여⎵인사⎵셋째,⎵자유민주주의와⎵안보의식이⎵미진한⎵인사⎵넷째,⎵2016년⎵총선⎵‘⎵진⎵박⎵공천’⎵연루⎵인사⎵다섯째,⎵최순⎵실⎵국정⎵농단⎵사태⎵관련⎵인사⎵여섯째,⎵당⎵분열⎵조장⎵인사⎵일곱째,⎵존재감이⎵미약한⎵영남⎵다⎵선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다.
##### Twitter
> 조강특위⎵위원장인⎵김용태⎵사무총장은⎵“2016년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순실⎵국정농단⎵사태⎵연루자를⎵먼저⎵청산해야⎵하는⎵것⎵아니냐는⎵외부⎵위원들⎵의견이⎵강하다”고⎵말했다.
>
> 친박과⎵비박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다.
>
> 조강특위는⎵이와⎵별도로⎵7가지⎵원칙을⎵밝혔다.
>
> 첫째,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째,⎵반(反)시장적⎵입법⎵참여⎵인사⎵셋째,⎵자유민주주의와⎵안보의식이⎵미진한⎵인사⎵넷째,⎵2016년⎵총선⎵‘진박⎵공천’⎵연루⎵인사⎵다섯째,⎵최순실⎵국정농단⎵사태⎵관련⎵인사⎵여섯째,⎵당⎵분열⎵조장⎵인사⎵일곱째,⎵존재감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다.
##### Eunjeon
> 조강⎵특위⎵위원장인⎵김용태⎵사무총장은⎵“⎵2016⎵년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순실⎵국정⎵농단⎵사태⎵연루자를⎵먼저⎵청산해야⎵하는⎵것⎵아니냐는⎵외부⎵위원들⎵의견이⎵강하다⎵”⎵고⎵말했다⎵.
>
> 친⎵박과⎵비박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다⎵.
>
> 조강⎵특위는⎵이와⎵별도로⎵7⎵가지⎵원칙을⎵밝혔다⎵.
>
> 첫째⎵,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째⎵,⎵반⎵(⎵反⎵)⎵시장적⎵입법⎵참여⎵인사⎵셋째⎵,⎵자유민주주의와⎵안보⎵의식이⎵미진한⎵인사⎵넷째⎵,⎵2016⎵년⎵총선⎵‘⎵진박⎵공천⎵’⎵연루⎵인사⎵다섯째⎵,⎵최순⎵실⎵국정⎵농단⎵사태⎵관련⎵인사⎵여섯째⎵,⎵당⎵분열⎵조장⎵인사⎵일곱째⎵,⎵존재감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이⎵다⎵.
##### KOMORAN
> 조강특위⎵위원장인⎵김용태⎵사무총장은⎵“2016년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순실⎵국정농단⎵사태⎵연루자를⎵먼저⎵청산하여야⎵하는⎵것⎵아니냐는⎵외부⎵위원들⎵의견이⎵강하다”고⎵말하였다.
>
> 친박과⎵비박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다.
>
> 조강특위는⎵이와⎵별도로⎵7가지⎵원칙을⎵밝혔다.
>
> 첫째,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째,⎵반(反)시장적⎵입법⎵참여⎵인사⎵셋째,⎵자유민주주의와⎵안보의식이⎵미진한⎵인사⎵넷째,⎵2016년⎵총선⎵‘진박⎵공천’⎵연루⎵인사⎵다섯째,⎵최순실⎵국정농단⎵사태⎵관련⎵인사⎵여섯째,⎵당⎵분열⎵조장⎵인사⎵일곱째,⎵존재감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다.
##### Rhino
> 조강특위⎵위원장인⎵김용태⎵사무총장은⎵“⎵2016년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순실⎵국정농단⎵사태⎵연루자를⎵먼저⎵청산해야⎵하는⎵것⎵아니냐는⎵외부⎵위원들⎵의견이⎵강하다⎵”⎵고⎵말했다⎵.
>
> 친박과⎵비박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다⎵.
>
> 조강특위는⎵이와⎵별도로⎵7가지⎵원칙을⎵밝혔다⎵.
>
> 첫째⎵,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째⎵,⎵반⎵(⎵反⎵)⎵시장적⎵입법⎵참여⎵인사⎵셋째⎵,⎵자유민주주의와⎵안보의식이⎵미진한⎵인사⎵넷째⎵,⎵2016년⎵총선⎵‘⎵진박⎵공천⎵’⎵연루⎵인사⎵다섯째⎵,⎵최순실⎵국정농단⎵사태⎵관련⎵인사⎵여섯째⎵,⎵당⎵분열⎵조장⎵인사⎵일곱째⎵,⎵존재감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다⎵.
##### Arirang
> 조⎵강⎵특위⎵위원장인⎵김⎵용태⎵사무⎵총장은⎵“⎵2016⎵년⎵총선⎵참패에⎵책임이⎵있는⎵이른⎵바 진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최⎵순실⎵국정⎵농단⎵사태⎵연루⎵자를⎵먼저⎵청산⎵해야⎵하는⎵것⎵아니냐는⎵외부⎵위원⎵들⎵의견이⎵강하다⎵”⎵고⎵말했다⎵.
>
> 친⎵박과⎵비⎵박의⎵한판⎵승부가⎵불가피⎵하다는⎵의미를⎵내포한다⎵.
>
> 조강⎵특위는⎵이와⎵별도로⎵7⎵가지⎵원칙을⎵밝혔다⎵.
>
> 첫째⎵,⎵대여⎵투쟁에⎵미온⎵적인⎵인사⎵둘째⎵,⎵반⎵(⎵反⎵)⎵시장⎵적⎵입법⎵참여⎵인사⎵셋째⎵,⎵자유⎵민주⎵주의와⎵안보⎵의식이⎵미진한⎵인사⎵넷째⎵,⎵2016⎵년⎵총선⎵‘⎵진박⎵공천⎵’⎵연루⎵인사⎵다섯⎵째⎵,⎵최⎵순실⎵국정⎵농단⎵사태⎵관련⎵인사⎵여섯째⎵,⎵당⎵분열⎵조장⎵인사⎵일곱⎵째⎵,⎵존재⎵감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다⎵.
##### Hannanum
> 조강특위⎵위원장인⎵김용태⎵사무총장은⎵“2016년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순실⎵국정농단⎵사태⎵연루자를⎵먼저⎵청산해야⎵하는⎵것⎵아니냐는⎵외부⎵위원들⎵의견이⎵강하다”고⎵말했다⎵.
>
> 친박과⎵비박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다⎵.
>
> 조강특위는⎵이와⎵별도로⎵7가지⎵원칙을⎵밝혔다⎵.
>
> 첫째,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째,⎵반(反)시장적⎵입법⎵참여⎵인사⎵셋째,⎵자유민주주의와⎵안보의식이⎵미진한⎵인사⎵넷째,⎵2016년⎵총선⎵‘진박⎵공천’⎵연루⎵인사⎵다섯째,⎵최순실⎵국정농단⎵사태⎵관련⎵인사⎵여섯째,⎵당⎵분열⎵조장⎵인사⎵일곱째,⎵존재감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다⎵.
##### Daon
> 조강특위⎵위원장인⎵김용태⎵사무총장은⎵“2016년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순실⎵국정농단⎵사태⎵연루자를⎵먼저⎵청산해야⎵하는⎵것⎵아니냐는⎵외부⎵위원들⎵의견이⎵강하다”고⎵말했다.
>
> 친박과⎵비박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다.
>
> 조강특위는⎵이와⎵별도로⎵7가지⎵원칙을⎵밝혔다.
>
> 첫째,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째,⎵반(反)시장적⎵입법⎵참여⎵인사⎵셋째,⎵자유민주주의와⎵안보의식이⎵미진한⎵인사⎵넷째,⎵2016년⎵총선⎵‘진박⎵공천’⎵연루⎵인사⎵다섯째,⎵최순실⎵국정농단⎵사태⎵관련⎵인사⎵여섯째,⎵당⎵분열⎵조장⎵인사⎵일곱째,⎵존재감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다.
##### ETRI
> 조강특위⎵위원장인⎵김용태⎵사무총장은⎵“2016년⎵총선⎵참패에⎵책임이⎵있는⎵이른바⎵진박⎵의원과⎵박근혜⎵전⎵대통령⎵탄핵을⎵가져온⎵최순실⎵국정농단⎵사태⎵연루자를⎵먼저⎵청산해야⎵하는⎵것⎵아니냐는⎵외부⎵위원들⎵의견이⎵강하다”고⎵말했다.
>
> 친박과⎵비박의⎵한판⎵승부가⎵불가피하다는⎵의미를⎵내포한다.
>
> 조강특위는⎵이와⎵별도로⎵7가지⎵원칙을⎵밝혔다.
>
> 첫째,⎵대여⎵투쟁에⎵미온적인⎵인사⎵둘째,⎵반(反)시장적⎵입법⎵참여⎵인사⎵셋째,⎵자유민주주의와⎵안보의식이⎵미진한⎵인사⎵넷째,⎵2016년⎵총선⎵‘진박⎵공천’⎵연루⎵인사⎵다섯째,⎵최순실⎵국정농단⎵사태⎵관련⎵인사⎵여섯째,⎵당⎵분열⎵조장⎵인사⎵일곱째,⎵존재감이⎵미약한⎵영남⎵다선⎵인사⎵등을⎵인적⎵쇄신⎵대상으로⎵삼겠다는⎵것이다.
#### 품사 분석
##### KKMA
```text
조강/NNG 특위/NNG 위원장/NNG+이/VCP+ㄴ/ETM 김/NNB 용태/NNG 사무/NNG+총장/NNG+은/JX “/SS+2016/NR 년/NNB 총선/NNG 참패/NNG+에/JKB 책임/NNG+이/JKS 있/VV+는/ETM 이른바/MAG 진박/NF 의원/NNG+과/JKB 박근/NNG 혜/NF 전/NNG 대통령/NNG 탄핵/NNG+을/JKO 가져오/VV+ㄴ/ETM 최/NNP+순/XSN 실/NNG 국정/NNG 농단/NNG 사태/NNG 연루자/NNG+를/JKO 먼저/MAG 청산/NNG+하/XSV+어야/EC 하/VX+는/ETM 것/NNB 아니/VCN+냐/EF
늘/VA+ㄴ/ETM 외부/NNG 위원/NNG+들/XSN 의견/NNG+이/JKS 강/NNG+하/XSV+다/EC ”/SS+고/JC 말/NNG+하/XSV+었/EP+다/EF+./SF
치/VV+ㄴ/ETM 박과/NNG 비/NNG 박의/NNG 한판/NNG 승부/NNG+가/JKS 불가피/XR+하/XSA+다/EC+는/JX 의미/NNG+를/JKO 내포/NNG+하/XSV+ㄴ다/EF+./SF
조강/NNG 특위/NNG+는/JX 이/NP+와/JKB 별도/NNG+로/JKB 7/NR+가지/NNM 원칙/NNG+을/JKO 밝히/VV+었/EP+다/EF+./SF
첫째/NR+,/SP 대여/NNG 투쟁/NNG+에/JKB 미온/NNG+적/XSN+이/VCP+ㄴ/ETM 인사/NNG 둘째/NR+,/SP 반/XPN+(/SS 反/SH+)/SS 시장/NNG+적/XSN 입법/NNG 참여/NNG 인사/NNG 셋째/NR+,/SP 자유/NNG+민주주의/NNG+와/JC 안보/NNG+의식/NNG+이/JKS 미진/XR+하/XSA+ㄴ/ETM 인사/NNG 넷째/NR+,/SP 2016/NR+년/NNM 총선/NNG ‘/SS 지/VX+ㄴ/ETM 박/NNM 공천/NNG+’/SS 연루/NNG 인사/NNG 다섯째/NR+,/SP 최/NNP+순/XSN 실/NNG 국정/NNG 농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NR+,/SP 당/XPN 분열/NNG 조장/NNG 인사/NNG 일곱째/NR+,/SP 존재/NNG+감/XSN+이/JKS 미약/XR+하/XSA+ㄴ/ETM 영남/NNG 다/MAG 선/NNG 인사/NNG 등/NNB+을/JKO 인적/NNG 쇄신/NNG 대상/NNG+으로/JKB 삼/VV+겠/EP+다는/ETM 것/NNB+이/VCP+다/EF+./SF
```

##### Twitter
```text
조강/NNG+특위/NNG 위원장/NNG+인/JX 김용태/NNG 사무/NNG+총장/NNG+은/JX “/SL+2016년/NR 총선/NNG 참패/NNG+에/JX 책임/NNG+이/JX 있는/VA 이른바/MAG 진박/NNG 의원/NNG+과/JX 박근혜/NNG 전/NNG 대통령/NNG 탄핵/NNG+을/JX 가져온/VV 최/NNG+순/MM+실/NNG 국정/NNG+농단/NNG 사태/NNG 연루/NNG+자/XSO+를/JX 먼저/NNG 청산/NNG+해야/VV 하는/VV 것/NNG 아니냐는/VA 외부/NNG 위원/NNG+들/XSO 의견/NNG+이/JX 강하다/VA+”/SL+고/NNG 말/NNG+했다/VV+./SF
친박/NNG+과/JX 비/NNG+박의/NNG 한판/NNG 승부/NNG+가/JX 불가피하다는/VA 의미/NNG+를/JX 내/MM+포한/NNG+다/MAG+./SF
조강특위/NNG+는/JX 이/NNG+와/JX 별도/NNG+로/JX 7/NR+가지/NNG 원칙/NNG+을/JX 밝혔다/VV+./SF
첫째/NNG+,/SF 대여/NNG 투쟁/NNG+에/JX 미온/NNG+적/XSO+인/JX 인사/NNG 둘째/NNG+,/SF 반/NNG+(/SF+反/SL+)/SF+시장/NNG+적/XSO 입법/NNG 참여/NNG 인사/NNG 셋째/MM+,/SF 자유/NNG+민주주의/NNG+와/JX 안보/NNG+의식/NNG+이/JX 미진한/VA 인사/NNG 넷째/NNG+,/SF 2016년/NR 총선/NNG ‘/SL+진박/NNG 공천/NNG+’/SF 연루/NNG 인사/NNG 다섯째/MM+,/SF 최/NNG+순/MM+실/NNG 국정/NNG+농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/MM+,/SF 당/NNG 분열/NNG 조장/NNG 인사/NNG 일곱째/MM+,/SF 존재/NNG+감/NNG+이/JX 미약/NNG+한/JX 영남/NNG 다/MAG+선/NNG 인사/NNG 등/NNG+을/JX 인적/NNG 쇄신/NNG 대상/NNG+으로/JX 삼겠다는/VV 것/NNG+이다/JX+./SF
```

##### Eunjeon
```text
조강/NNG 특위/NNG 위원/NNG+장/NNG+이/VCP+ᆫ/ETM 김용태/NNP 사무/NNG+총장/NNG+은/JX “/SS 2016/SN 년/NNM 총선/NNG 참패/NNG+에/JKB 책임/NNG+이/JKS 있/VA+는/ETM 이른바/MAJ 진박/NNG 의원/NNG+과/JC 박근혜/NNP 전/MM 대통령/NNG 탄핵/NNG+을/JKO 가져오/VV+ᆫ/ETM 최순실/NA 국정/NNG 농단/NNG 사태/NNG 연루/NNG+자/NNG+를/JKO 먼저/MAG 청산/NNG+하/XSV+아야/EC 하/VV+는/ETM 것/NNB 아니/VCN+냐는/ETM 외부/NNG 위원/NNG+들/XSN 의견/NNG+이/JKS 강하/VA+다/EC ”/SS 고/EC 말/NNG+하/XSV+았/EP+다/EF ./SF
치/VV+ᆫ/ETM 박/NNG+과/NNG 비박/NNG+의/JKG 한/NNG+판/NNG 승부/NNG+가/JKS 불가피/XR+하/XSA+다는/ETM 의미/NNG+를/JKO 내포/NNG+하/XSV+ᆫ다/EF ./SF
조강/NNG 특위/NNG+는/JX 이/NP+와/JKB 별도/NNG+로/JKB 7/SN 가지/NNM 원칙/NNG+을/JKO 밝히/VV+었/EP+다/EF ./SF
첫째/NR ,/SP 대여/NNG 투쟁/NNG+에/JKB 미온/NNG+적/XSN+이/VCP+ᆫ/ETM 인사/NNG 둘째/NR ,/SP 반/NNG (/SS 反/SH )/SS 시장/NNG+적/XSN 입법/NNG 참여/NNG 인사/NNG 셋째/NR ,/SP 자유/NNG+민주/NNG+주의/NNG+와/JC 안보/NNG 의식/NNG+이/JKS 미진/XR+하/XSA+ᆫ/ETM 인사/NNG 넷째/NR ,/SP 2016/SN 년/NNM 총선/NNG ‘/SW 진박/NNG 공천/NNG ’/SW 연루/NNG 인사/NNG 다섯째/NR ,/SP 최순/NNP 실/NNG 국정/NNG 농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NR ,/SP 당/NNG 분열/NNG 조장/NNG 인사/NNG 일곱째/NR ,/SP 존재/NNG+감/NNG+이/JKS 미약/XR+하/XSA+ᆫ/ETM 영남/NNP 다선/NNG 인사/NNG 등/NNB+을/JKO 인/NNG+적/XSN 쇄신/NNG 대상/NNG+으로/JKB 삼/VV+겠/EP+다는/ETM 것/NNB+이/VCP 다/EF ./SF
```

##### KOMORAN
```text
조강/NNP+특위/NNG 위원장/NNG+이/VCP+ㄴ/ETM 김용태/NNP 사무총장/NNG+은/JX “/SS+2016년/NNP 총선/NNG 참패/NNG+에/JKB 책임/NNG+이/JKS 있/VV+는/ETM 이른바/MAJ 지/VX+ㄴ/ETM+박/NNP 의원/NNG+과/JC 박근혜/NNP 전/MM 대통령/NNG 탄핵/NNP+을/JKO 가져오/VV+ㄴ/ETM 최순/NNP+실/NNP 국정/NNG+농/NNG+단/NNG 사태/NNG 연루자/NNG+를/JKO 먼저/MAG 청산/NNG+하/XSV+아야/EC 하/VV+는/ETM 것/NNB 아니/VCN+냐는/ETM 외부/NNG 위원/NNG+들/XSN 의견/NNG+이/JKS 강하/VA+다/EC+”/SS+고/JKQ 말/NNG+하/XSV+았/EP+다/EF+./SF
친박/NNP+과/JC 비/XPN+박/NNP+의/JKG 한판/NNG 승부/NNG+가/JKS 불가피/XR+하/XSA+다는/ETM 의미/NNG+를/JKO 내포/NNG+하/XSV+ㄴ다/EF+./SF
조강/NNP+특위/NNG+는/JX 이/NP+와/JKB 별도/NNG+로/JKB 7/SN+가지/NNB 원칙/NNG+을/JKO 밝히/VV+었/EP+다/EF+./SF
첫째/NR+,/SP 대/NNB+이/VCP+어/EC 투쟁/NNG+에/JKB 미온/NNG+적/XSN+이/VCP+ㄴ/ETM 인사/NNG 둘째/NR+,/SP 반/NNG+(/SS+反/SH+)/SS+시장/NNG+적/XSN 입법/NNP 참여/NNG 인사/NNG 셋째/NR+,/SP 자유민주주의/NNP+와/JC 안보/NNG+의식/NNG+이/JKS 미진/XR+하/XSA+ㄴ/ETM 인사/NNG 넷째/NR+,/SP 2016/SN+년/NNB 총선/NNG ‘/SS+진/NNP+박/NNP 공천/NNP+’/SS 연루/NNG 인사/NNG 다섯째/NR+,/SP 최순/NNP+실/NNP 국정/NNG+농/NNG+단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NR+,/SP 당/NNG 분열/NNG 조장/NNG 인사/NNG 일곱째/NR+,/SP 존재/NNG+감/NNG+이/JKS 미약/XR+하/XSA+ㄴ/ETM 영남/NNP 다선/NNG 인사/NNG 등/NNB+을/JKO 인적/NNG 쇄신/NNG 대상/NNG+으로/JKB 삼/VV+겠/EP+다는/ETM 것/NNB+이/VCP+다/EF+./SF
```

##### Rhino
```text
조강/NNG+특위/NNG 위원장/NNG+이/VCP+ㄴ/ETM 김용태/NNP 사무총장/NNG+은/JX “/SS 2016/SN+년/NNB 총선/NNG 참패/NNG+에/JKB 책임/NNG+이/JKS 있/VX+는/ETM 이른바/MAG 지/VV+ㄴ/ETM+박/NNP 의원/NNG+과/JC 박근혜/NNP 전/NNG 대통령/NNG 탄핵/NNG+을/JKO 가지/VV+어/EC+오/VX+ㄴ/ETM 최/NNP+순실/NNG 국정/NNG+농단/NNG 사태/NNG 연루자/NNG+를/JKO 먼저/MAG 청산하/VV+아야/EC 하/VV+는/ETM 것/NNB 아니/VCN+냐는/ETM 외부/NNG 위원/NNG+들/XSN 의견/NNG+이/JKS 강하/VA+다/EF ”/SS 고/JKQ 말하/VV+았/EP+다/EF ./SF
친/XPN+박/NNG+과/JC 비/NNG+박/NNG+의/JKG 한판/NNG 승부/NNG+가/JKS 불가피/XR+하/VV+다는/ETM 의미/NNG+를/JKO 내포하/VV+ㄴ다/EF ./SF
조강/NNG+특위/NNG+는/JX 이/NP+와/JC 별도/NNG+로/JKB 7/SN+가지/NNB 원칙/NNG+을/JKO 밝히/VV+었/EP+다/EF ./SF
첫째/NR ,/SP 대여/NNG 투쟁/NNG+에/JKB 미온/NNG+적/XSN+이/VCP+ㄴ/ETM 인사/NNG 둘째/NR ,/SP 반/NNG (/SS 反/SH )/SS 시장/NNG+적/XSN 입법/NNG 참여/NNG 인사/NNG 셋째/NR ,/SP 자유/NNG+민주주의/NNG+와/JC 안보/NNG+의식/NNG+이/JKS 미진/XR+하/VV+ㄴ/ETM 인사/NNG 넷째/NR ,/SP 2016/SN+년/NNB 총선/NNG ‘/SS 지/VV+ㄴ/ETM+박/VV 공천/NNG ’/SS 연루/NNG 인사/NNG 다섯째/NR ,/SP 최/NNP+순실/NNG 국정/NNG+농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NR ,/SP 당/MM 분열/NNG 조장/NNG 인사/NNG 일곱째/NR ,/SP 존재감/NNG+이/JKS 미약/XR+하/VV+ㄴ/ETM 영남/NNP 다선/NNG 인사/NNG 등/NNB+을/JKO 인적/NNG 쇄신/NNG 대상/NNG+으로/JKB 삼/VV+겠/EP+다는/ETM 것/NNB+이다/EF ./SF
```

##### Arirang
```text
조/NNG 강/NNG 특위/NNG 위원장/NNG+이/XSV+ㄴ/EF 김/NNG 용태/NNG 사무/NNG 총장/NNG+은/JX “/SS 2016/NNG 년/NNG 총선/NNG 참패/NNG+에/JX 책/NNG+이/XSV+ㅁ/ETN+이/JX 있/VV+는/EF 이르/VV+ㄴ/EF 바 진박/NNG 의원/NNG+과/JX 박근혜/NNG 전/NNG 대통령/NNG 탄핵/NNG+을/JX 가져오/VV+ㄴ/EF 최/NNG 순실/NNG 국정/NNG 농단/NNG 사태/NNG 연루/NNG 자르/VV+ㄹ/EF 먼저/NNG 청산/NNG 하/VV+어야/EF 하/VV+는/EF 것/NNG 아니/VV+냐는/EF 외부/NNG 위원/NNG 들/NNG 의견/NNG+이/JX 강하/VV+어다/EF ”/SS 고/NNG 말/NNG+하/XSV+었/EP+다/EF ./SF
친/NNG 박/NNG+과/JX 비/NNG 박/NNG+의/JX 한판/NNG 승부/NNG+가/JX 불가피/NNG 하/VV+다는/EF 의미/NNG+를/JX 내포/NNG+하/XSV+ㄴ다/EF ./SF
조강/NNG 특위/NNG+는/JX 입/VV+아/EF 별도/NNG+로/JX 7/NNG 가지/NNG 원칙/NNG+을/JX 밝히/VV+었/EP+다/EF ./SF
첫째/NNG ,/SP 대여/NNG 투쟁/NNG+에/JX 미온/NNG 적/NNG+이/XSV+ㄴ/EF 인사/NNG 둘째/NNG ,/SP 반/NNG (/SS 反/NNG )/SS 시장/NNG 적/NNG 입법/NNG 참여/NNG 인사/NNG 셋째/NNG ,/SP 자유/NNG 민주/NNG 주의/NNG+와/JX 안보/NNG 의식/NNG+이/JX 미진/NNG+하/XSV+ㄴ/EF 인사/NNG 넷째/NNG ,/SP 2016/NNG 년/NNG 총선/NNG ‘/SS 진박/NNG 공천/NNG ’/SS 연루/NNG 인사/NNG 다섯/NNG 째/NNG ,/SP 최/NNG 순실/NNG 국정/NNG 농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NNG ,/SP 당/NNG 분열/NNG 조장/NNG 인사/NNG 일곱/NNG 째/NNG ,/SP 존재/NNG 감/NNG+이/JX 미약/NNG+하/XSV+ㄴ/EF 영남/NNG 다선/NNG 인사/NNG 등/NNG+을/JX 인적/NNG 쇄신/NNG 대상/NNG+으로/JX 삼/VV+겠/EP+다는/EF 것/NNG+이/XSV+다/EF ./NA
```

##### Hannanum
```text
조강특위/NNG 위원장/NNG+이/VCP+ㄴ/ETM 김용태/NNG 사무총장/NNG+은/JX “2016년/NNG 총선/NNG 참패/NNG+에/JKB 책/NNG+이/VCP+ㅁ/ETN+이/JKS 있/VX+는/ETM 이른바/MAJ 진박/NNG 의원/NNG+과/JC 박근혜/NNG 전/NNB 대통령/NNG 탄핵/NNG+을/JKO 가/VV+아/EC+지/VX+어/EC+오/VX+ㄴ/ETM 최순실/NNG 국정농단/NNG 사태/NNG 연루자/NNG+를/JKO 먼저/MAG 청산/NNG+해/NNG+이/VCP+야/EF 하/VV+는/ETM 것/NNB 아니/VA+냐/EF+는/ETM 외부/NNG 위원/NNG+들/NNG 의견/NNG+이/JKC 강하다”고/NNG 말/NNG+하/XSV+었/EP+다/EF ./SF
친박/NNG+과/JC 비/XPN+박/NNG+의/JKG 한/NR+판/NNM 승부/NNG+가/JKC 불가피/NNG+하/XSA+다/EF+는/ETM 의미/NNG+를/JKO 내포/NNG+하/XSV+ㄴ다/EF ./SF
조강특위/NNG+는/JX 이/NP+와/JC 별도/NNG+로/JKB 7/NR+가지/NNM 원칙/NNG+을/JKO 밝히/VV+었/EP+다/EF ./SF
첫째/NR+,/SP 대/NNM+이/VCP+어/EC 투쟁/NNG+에/JKB 미온적/NNG+이/VCP+ㄴ/ETM 인사/NNG 둘째/NR+,/SP 반(反)시장적/NNG 입법/NNG 참여/NNG 인사/NNG 셋째/NR+,/SP 자유/NNG+민주주의/NNG+와/JC 안보의식/NNG+이/JKC 미진/NNG+한/NNG 인사/NNG 넷째/NR+,/SP 2016/NR+년/NNM 총선/NNG ‘진박/NNG 공천’/NNG 연루/NNG 인사/NNG 다섯째/NR+,/SP 최순실/NNG 국정농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NR+,/SP 당/NNG 분열/NNG 조장/NNG 인사/NNG 일곱째/NR+,/SP 존재/NNG+감/NNG+이/JKC 미약/NNG+한/NNG 영남/NNG 다/MAG+선/JX 인사/NNG 등/NNM+을/JKO 일/VV+ㄴ/ETM+적/NNB 쇄신/NNG 대상/NNG+으로/JKB 삼/VV+겠/EP+다/EF+는/ETM 것/NNB+이/VCP+다/EF ./SF
```

##### Daon
```text
조강/NNG+특위/NNG 위원장/NNG+이/VCP+ㄴ/ETM 김용태/NNP 사무총장/NNG+은/JX “/SW+2016/SN+년/NNB 총선/NNG 참패/NNG+에/JKB 책임/NNG+이/JKS 있/VA+는/ETM 이른바/MAG 진박/NNG 의원/NNG+과/JC 박근혜/NNP 전/NNG 대통령/NNG 탄핵/NNG+을/JKO 가져오/VV+ㄴ/ETM 최순/NNP+실/NNB 국정농단/NNG 사태/NNG 연루자/NNG+를/JKO 먼저/MAG 청산/NNG+하/XSV+어야/EC 하/VV+는/ETM 것/NNB 아니/VCN+냐는/ETM 외부/NNG 위원/NNG+들/XSN 의견/NNG+이/JKS 강하/VA+다/EF+”/SW+고/EC 말/NNG+하/XSV+었/EP+다/EF+./SF
치/VV+ㄴ/ETM+박/NNG+과/JC 비박/NNG+의/JKG 한판/NNG 승부/NNG+가/JKS 불가피/NNG+하/XSA+다는/ETM 의미/NNG+를/JKO 내포/NNG+하/XSV+ㄴ다/EF+./SF
조강/NNG+특위/NNG+는/JX 이/NP+와/JKB 별도/NNG+로/JKB 7/SN+가지/NNB 원칙/NNG+을/JKO 밝히/VV+었/EP+다/EF+./SF
첫째/NR+,/SP 대여/NNG 투쟁/NNG+에/JKB 미온/NNG+적/XSN+이/VCP+ㄴ/ETM 인사/NNG 둘째/NR+,/SP 반/NNG+(/SP+反/SH+)/SP+시장/NNG+적/XSN 입법/NNG 참여/NNG 인사/NNG 셋째/NR+,/SP 자유민주주의/NNG+와/JC 안보/NNG+의식/NNG+이/JKS 미진/NNG+하/XSA+ㄴ/ETM 인사/NNG 넷째/NR+,/SP 2016/SN+년/NNB 총선/NNG ‘/SW+진박/NNG 공천/NNG+’/SW 연루/NNG 인사/NNG 다섯째/NR+,/SP 최순/NNP+실/NNB 국정농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NR+,/SP 당/NNG 분열/NNG 조장/NNG 인사/NNG 일곱/NR+째/XSN+,/SP 존재감/NNG+이/JKS 미약/NNG+하/XSA+ㄴ/ETM 영남/NNP 다선/NNG 인사/NNG 등/NNB+을/JKO 인/NNG+적/XSN 쇄신/NNG 대상/NNG+으로/JKB 삼/VV+겠/EP+다는/ETM 것/NNB+이/VCP+다/EF+./SF
```

##### ETRI
```text
조강/NNG+특위/NNG 위원장/NNG+이/VCP+ㄴ/ETM 김용태/NNP 사무총장/NNG+은/JX “/SS+2016/SN+년/NNB 총선/NNG 참패/NNG+에/JKB 책임/NNG+이/JKS 있/VA+는/ETM 이른바/MAG 진박/NNG 의원/NNG+과/JC 박근혜/NNP 전/MM 대통령/NNG 탄핵/NNG+을/JKO 가져오/VV+ㄴ/ETM 최순실/NNP 국정/NNG+농단/NNG 사태/NNG 연루자/NNG+를/JKO 먼저/MAG 청산하/VV+어야/EC 하/VX+는/ETM 것/NNB 아니/VCN+냐는/ETM 외부/NNG 위원/NNG+들/XSN 의견/NNG+이/JKS 강하/VA+다/EF+”/SS+고/JKQ 말하/VV+었/EP+다/EF+./SF
친박/NNG+과/JC 비박/NNG+의/JKG 한판/NNG 승부/NNG+가/JKS 불가피하/VA+다는/ETM 의미/NNG+를/JKO 내포하/VV+ㄴ다/EF+./SF
조강/NNG+특위/NNG+는/JX 이/NP+와/JKB 별도/NNG+로/JKB 7/SN+가지/NNB 원칙/NNG+을/JKO 밝히/VV+었/EP+다/EF+./SF
첫째/NR+,/SP 대여/NNG 투쟁/NNG+에/JKB 미온적/NNG+이/VCP+ㄴ/ETM 인사/NNG 둘째/NR+,/SP 반/XPN+(/SS+反/SH+)/SS+시장적/NNG 입법/NNG 참여/NNG 인사/NNG 셋째/NR+,/SP 자유민주주의/NNG+와/JC 안보/NNG+의식/NNG+이/JKS 미진하/VA+ㄴ/ETM 인사/NNG 넷째/NR+,/SP 2016/SN+년/NNB 총선/NNG ‘/SS+진박/NNG 공천/NNG+’/SS 연루/NNG 인사/NNG 다섯째/NR+,/SP 최순실/NNP 국정/NNG+농단/NNG 사태/NNG 관련/NNG 인사/NNG 여섯째/NR+,/SP 당/NNG 분열/NNG 조장/NNG 인사/NNG 일곱째/NR+,/SP 존재감/NNG+이/JKS 미약하/VA+ㄴ/ETM 영남/NNP 다선/NNG 인사/NNG 등/NNB+을/JKO 인적/NNG 쇄신/NNG 대상/NNG+으로/JKB 삼/VV+겠/EP+다는/ETM 것/NNB+이/VCP+다/EF+./SF
```

### 문장 번호 #1

원본 문장:
> 이렇듯 거대 양당이 모두 소용돌이 속에 진입하고 있다. 각 정당 내부의 싸움이 어떤 결론으로 끝날지는 아무도 모른다. 하지만 이 싸움이 향후 정국을 결정짓는 데 상당한 의미를 가질 수 있다. 누가 이기느냐는 중요하지 않다. 한쪽이 표면적으로는 진 것처럼 보여도 여론이 그렇게 생각하지 않으면 장기적 관점에서 승자가 될 수도 있다. 이 때문에 각 정당에서 벌어지는 싸움은 단순히 승자와 패자를 가르는 싸움이 아니다.
#### 어절 구분
##### KKMA
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도⎵모른다.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상⎵당한⎵의미를⎵가질⎵수⎵있다.
>
> 누가⎵이기느냐
>
> 는⎵중요하지⎵않다.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다.
##### Twitter
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도⎵모른다.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다.
>
> 누가⎵이기느냐는⎵중요하지⎵않다.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다.
##### Eunjeon
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다⎵.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도⎵모른다⎵.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다⎵.
>
> 누가⎵이기느냐는⎵중요하지⎵않다⎵.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다⎵.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다⎵.
##### KOMORAN
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도 모른다.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다.
>
> 누구가⎵이기느냐는⎵중요하지⎵않다.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵봐도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다.
##### Rhino
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다⎵.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도⎵모른다⎵.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다⎵.
>
> 누가⎵이기느냐는⎵중요하지⎵않다⎵.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다⎵.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다⎵.
##### Arirang
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다⎵.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날⎵지는⎵아⎵무도⎵모른다⎵.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다⎵.
>
> 누가⎵이기느냐는⎵중요⎵하지⎵않다⎵.
>
> 한쪽이⎵표면⎵적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기⎵적⎵관점에서⎵승자가⎵될⎵수도⎵있다⎵.
>
> 이 때⎵문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순⎵히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다⎵.
##### Hannanum
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다⎵.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도⎵모른다⎵.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다⎵.
>
> 누가⎵이기느냐는⎵중요하지⎵않다⎵.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다⎵.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다⎵.
##### Daon
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도⎵모른다.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다.
>
> 누가⎵이기느냐는⎵중요하지⎵않다.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다.
##### ETRI
> 이렇듯⎵거대⎵양당이⎵모두⎵소용돌이⎵속에⎵진입하고⎵있다.
>
> 각⎵정당⎵내부의⎵싸움이⎵어떤⎵결론으로⎵끝날지는⎵아무도⎵모른다.
>
> 하지만⎵이⎵싸움이⎵향후⎵정국을⎵결정짓는⎵데⎵상당한⎵의미를⎵가질⎵수⎵있다.
>
> 누가⎵이기느냐는⎵중요하지⎵않다.
>
> 한쪽이⎵표면적으로는⎵진⎵것처럼⎵보여도⎵여론이⎵그렇게⎵생각하지⎵않으면⎵장기적⎵관점에서⎵승자가⎵될⎵수도⎵있다.
>
> 이⎵때문에⎵각⎵정당에서⎵벌어지는⎵싸움은⎵단순히⎵승자와⎵패자를⎵가르는⎵싸움이⎵아니다.
#### 품사 분석
##### KKMA
```text
이렇/VA+듯/EC 거대/NNG 양당/NNG+이/JKS 모두/MAG 소용돌이/NNG 속/NNG+에/JKB 진입/NNG+하/XSV+고/EC 있/VX+다/EF+./SF
각/NNG 정당/NNG 내부/NNG+의/JKG 싸움/NNG+이/JKS 어떤/MM 결론/NNG+으로/JKB 끝나/VV+ㄹ지/EC+는/JX 아무/NP+도/JX 모르/VV+ㄴ다/EF+./SF
하지만/MAJ 이/MM 싸움/NNG+이/JKS 향후/NNG 정국/NNG+을/JKO 결정짓/VV+는/ETM 데/NNB 상/XSN 당한/NNG 의미/NNG+를/JKO 가지/VV+ㄹ/ETM 수/NNB 있/VV+다/EF+./SF
누/NP+가/JKS 이기/VV+느냐/EF
늘/VA+ㄴ/ETM 중요/NNG+하/XSA+지/EC 않/VX+다/EF+./SF
한쪽/NNG+이/JKS 표면/NNG+적/XSN+으로/JKB+는/JX 지/VX+ㄴ/ETM 것/NNB+처럼/JKB 보이/VV+어도/EC 여론/NNG+이/JKS 그렇/VA+게/EC 생각/NNG+하/XSV+지/EC 않/VX+으면/EC 장기/NNG+적/XSN 관점/NNG+에서/JKB 승자/NNG+가/JKC 되/VV+ㄹ/ETM 수/NNB+도/JX 있/VV+다/EF+./SF
이/MM 때문/NNB+에/JKB 각/NNG 정당/NNG+에서/JKB 벌어지/VV+는/ETM 싸움/NNG+은/JX 단순히/MAG 승자/NNG+와/JC 패자/NNG+를/JKO 가르/VV+는/ETM 싸움/NNG+이/JKC 아니/VCN+다/EF+./SF
```

##### Twitter
```text
이렇듯/VA 거대/NNG 양/MM+당/NNG+이/JX 모두/NNG 소용돌이/NNG 속/NNG+에/JX 진입/NNG+하고/JX 있다/VA+./SF
각/NNG 정당/NNG 내부/NNG+의/JX 싸움/NNG+이/JX 어떤/VA 결론/NNG+으로/JX 끝날지는/VV 아무/NNG+도/JX 모른다/VV+./SF
하지만/JC 이/NNG 싸움/NNG+이/JX 향후/NNG 정국/NNG+을/JX 결정/NNG+짓는/VV 데/NNG 상당한/VA 의미/NNG+를/JX 가질/VV 수/NNG 있다/VA+./SF
누가/NNG 이기느냐는/VV 중요하지/VA 않다/VV+./SF
한쪽/NNG+이/JX 표면/NNG+적/XSO+으로는/JX 진/NNG 것/NNG+처럼/JX 보여도/VV 여론/NNG+이/JX 그렇게/MAG 생각/NNG+하지/VV 않으면/VV 장기/NNG+적/XSO 관점/NNG+에서/JX 승자/NNG+가/JX 될/VV 수도/NNG 있다/VA+./SF
이/NNG 때문/NNG+에/JX 각/NNG 정당/NNG+에서/JX 벌어지는/VV 싸움/NNG+은/JX 단순히/VA 승자/NNG+와/JX 패자/NNG+를/JX 가르는/VV 싸움/NNG+이/JX 아니다/VA+./SF
```

##### Eunjeon
```text
이렇/VA+듯/EC 거대/NNG 양당/NNG+이/JKS 모두/MAG 소용돌이/NNG 속/NNG+에/JKB 진입/NNG+하/XSV+고/EC 있/VX+다/EF ./SF
각/MM 정당/NNG 내부/NNG+의/JKG 싸움/NNG+이/JKS 어떤/MM 결론/NNG+으로/JKB 끝나/VV+ᆯ지/EC+는/JX 아무/NP+도/JX 모르/VV+ᆫ다/EF ./SF
하지만/MAJ 이/MM 싸움/NNG+이/JKS 향후/NNG 정국/NNG+을/JKO 결정짓/VV+는/ETM 데/NNB 상당/NNG+하/XSA+ᆫ/ETM 의미/NNG+를/JKO 가지/VV+ᆯ/ETM 수/NNB 있/VV+다/EF ./SF
누구/NP+가/JKS 이기/VV+느냐는/ETM 중요/NNG+하/XSV+지/EC 않/VX+다/EF ./SF
한/NNG+쪽/NNG+이/JKS 표면/NNG+적/XSN+으로/JKB+는/JX 지/VX+ᆫ/ETM 것/NNB+처럼/JKB 보이/VV+어도/EC 여론/NNG+이/JKS 그렇게/MAG 생각/NNG+하/XSV+지/EC 않/VX+으면/EC 장기/NNG+적/XSN 관점/NNG+에서/JKB 승자/NNG+가/JKS 되/VV+ᆯ/ETM 수/NNB+도/JX 있/VA+다/EF ./SF
이/MM 때문/NNB+에/JKB 각/MM 정당/NNG+에서/JKB 벌어지/VV+는/ETM 싸움/NNG+은/JX 단순히/MAG 승자/NNG+와/JC 패자/NNG+를/JKO 가르/VV+는/ETM 싸움/NNG+이/JKC 아니/VCN+다/EF ./SF
```

##### KOMORAN
```text
이렇/VA+듯/EC 거대/XR 양당/NNG+이/JKS 모두/MAG 소용돌이/NNP 속/NNG+에/JKB 진입/NNG+하/XSV+고/EC 있/VX+다/EF+./SF
각/MM 정당/NNG 내부/NNG+의/JKG 싸움/NNG+이/JKS 어떤/MM 결론/NNG+으로/JKB 끝나/VV+ㄹ지/EC+는/JX 아무도 모른다/NNP+./SF
하지만/MAJ 이/MM 싸움/NNG+이/JKS 향후/NNG 정국/NNG+을/JKO 결정짓/VV+는/ETM 데/NNB 상당/NNG+하/XSV+ㄴ/ETM 의미/NNG+를/JKO 가지/VV+ㄹ/ETM 수/NNB 있/VX+다/EF+./SF
누구/NP+가/JKS 이기/VV+느냐는/ETM 중요/XR+하/XSA+지/EC 않/VX+다/EF+./SF
한쪽/NNG+이/JKS 표면/NNG+적/XSN+으로/JKB+는/JX 지/VX+ㄴ/ETM 것/NNB+처럼/JKB 보/VV+아도/EC 여론/NNG+이/JKS 그렇/VA+게/EC 생각/NNG+하/XSV+지/EC 않/VX+으면/EC 장기/NNG+적/XSN 관점/NNG+에서/JKB 승자/NNG+가/JKS 되/VV+ㄹ/ETM 수/NNB+도/JX 있/VX+다/EF+./SF
이/MM 때문/NNB+에/JKB 각/MM 정당/NNG+에서/JKB 벌어지/VV+는/ETM 싸움/NNG+은/JX 단순히/MAG 승자/NNG+와/JC 패자/NNP+를/JKO 가르/VV+는/ETM 싸움/NNG+이/JKS 아니/VCN+다/EF+./SF
```

##### Rhino
```text
이렇/VA+듯/EC 거대/NNG 양당/NNG+이/JKS 모두/MAG 소용돌이/NNG 속/NNG+에/JKB 진입하/VV+고/EC 있/VX+다/EF ./SF
각/MM 정당/NNG 내부/NNG+의/JKG 싸움/NNG+이/JKS 어떤/MM 결론/NNG+으로/JKB 끝나/VV+ㄹ지/EC+는/JX 아무/MM+도/JX 모르/VV+ㄴ다/EF ./SF
하지만/MAJ 이/MM 싸움/NNG+이/JKS 향후/NNG 정국/NNG+을/JKO 결정짓/VV+는/ETM 데/NNG 상당/XR+하/VV+ㄴ/ETM 의미/NNG+를/JKO 가지/VV+ㄹ/ETM 수/NNB 있/VX+다/EF ./SF
누구/NNG+가/JKS 이기/VV+느냐는/ETM 중요하/VV+지/EC 않/VX+다/EF ./SF
한쪽/NNG+이/JKS 표면적/NNG+으로/JKB+는/JX 지/VV+ㄴ/ETM 것/NNB+처럼/JKB 보이/VV+어도/EC 여론/NNG+이/JKS 그렇게/MAG 생각하/VV+지/EC 않/VX+으면/EC 장기/NNG+적/XSN 관점/NNG+에서/JKB 승자/NNG+가/JKS 되/VV+ㄹ/ETM 수도/NNG 있/VX+다/EF ./SF
이/MM 때문/NNB+에/JKB 각/MM 정당/NNG+에서/JKB 벌어지/VV+는/JX 싸움/NNG+은/JX 단순히/MAG 승자/NNP+와/JC 패자/NNG+를/JKO 가르/VV+는/ETM 싸움/NNG+이/JKS 아니/VCN+다/EF ./SF
```

##### Arirang
```text
이렇듯/NNG 거대/NNG 양당/NNG+이/JX 모두/MAG 소용돌이/NNG 속/NNG+에/JX 진입/NNG+하/XSV+고/EF 있/VV+다/EF ./SF
각/NNG 정당/NNG 내부/NNG+의/JX 싸우/VV+ㅁ/ETN+이/JX 어떤/NNG 결론/NNG+으로/JX 끝나/VV+ㄹ/EF 지/VV+는/EF /NNG+아/JX 무도/NNG 모르/VV+ㄴ다/EF ./SF
하/VV+지만/EF 이/NNG 싸우/VV+ㅁ/ETN+이/JX 향후/NNG 정국/NNG+을/JX 결정/NNG+짓/XSV+는/EF 데/NNG 상/NNG+당하/XSV+ㄴ/EF 의미/NNG+를/JX 가/VV+질/EF 수/NNG 있/VV+다/EF ./SF
누가/NNG 이기/VV+느냐는/EF 중요/NNG 하지/NNG 않/VV+다/EF ./SF
한쪽/NNG+이/JX 표면/NNG 적/NNG+으로는/JX 진/NNG 것/NNG+처럼/JX 보이/VV+어도/EF 여론/NNG+이/JX 그렇/VV+게/EF 생각/NNG+하/XSV+지/EF 않/VV+으면/EF 장기/NNG 적/NNG 관점/NNG+에서/JX 승자/NNG+가/JX 될/NNG 수도/NNG 있/VV+다/EF ./SF
이 때/NNG 문/NNG+에/JX 각/NNG 정당/NNG+에서/JX 벌/VV+어/EC+지/VX+는/EF 싸우/VV+ㅁ/ETN+은/JX 단순/NNG 히/NNG 승자/NNG+와/JX 패자/NNG+를/JX 가르/VV+는/EF 싸우/VV+ㅁ/ETN+이/JX 아니/VV+다/EF ./NA
```

##### Hannanum
```text
이렇/VA+듯/EC 거대/NNG 양당/NNG+이/JKC 모두/MAG 소용돌이/NNG 속/NNG+에/JKB 진입/NNG+하고/JC 있/VX+다/EF ./SF
각/MM 정당/NNG 내부/NNG+의/JKG 싸우/VV+ㅁ/ETN+이/JKS 어떤/MM 결론/NNG+으로/JKB 끝나/VV+ㄹ지/EC+는/JX 아무/NP+도/JX 모르/VV+ㄴ다/EF ./SF
하/VV+지만/EC 이/MM 싸우/VV+ㅁ/ETN+이/JKS 향후/MAG 정국/NNG+을/JKO 결정짓/VV+는/ETM 데/NNB 상당/NNG+한/NNG 의미/NNG+를/JKO 가/VV+아/EC+지/VX+ㄹ/ETM 수/NNB 있/VX+다/EF ./SF
누구/NP+가/JKS 이/NP+이/VCP+기/ETN+이/VCP+느냐/EF+는/ETM 중요/NNG+하/XSA+지/EC 않/VX+다/EF ./SF
하/VV+ㄴ/ETM+쪽/NNB+이/JKC 표면/NNG+적/NNG+으로/JKB+는/JX 지/VX+ㄴ/ETM 것/NNB+처럼/JKB 보이/VV+어도/EC 여론/NNG+이/JKC 그렇/VA+게/EC 생각/NNG+하/XSV+지/EC 않/VX+으면/EC 장기/NNG+적/NNG 관점/NNG+에서/JKB 승자/NNG+가/JKS 되/VV+ㄹ/ETM 수/NNB+도/JX 있/VX+다/EF ./SF
이/MM 때문/NNB+에/JKB 각/MM 정당/NNG+에서/JKB 벌/VV+어/EC+지/VX+는/ETM 싸우/VV+ㅁ/ETN+은/JX 단순히/MAG 승자/NNG+와/JC 패자/NNG+를/JKO 가르/VV+는/ETM 싸우/VV+ㅁ/ETN+이/JKS 아니/VA+다/EF+./SF ./SF
```

##### Daon
```text
이렇/VA+듯/EC 거대/NNG 양당/NNG+이/JKS 모두/MAG 소용돌이/NNG 속/NNG+에/JKB 진입/NNG+하/XSV+고/EC 있/VX+다/EF+./SF
각/MM 정당/NNG 내부/NNG+의/JKG 싸움/NNG+이/JKS 어떤/MM 결론/NNG+으로/JKB 끝나/VV+ㄹ지/EC+는/JX 아무/NP+도/JX 모르/VV+ㄴ다/EF+./SF
하지만/MAJ 이/MM 싸움/NNG+이/JKS 향후/NNG 정국/NNG+을/JKO 결정짓/VV+는/ETM 데/NNB 상당/NNG+하/XSA+ㄴ/ETM 의미/NNG+를/JKO 가지/VV+ㄹ/ETM 수/NNB 있/VA+다/EF+./SF
누구/NP+가/JKS 이기/VV+느냐는/ETM 중요/NNG+하/XSA+지/EC 않/VX+다/EF+./SF
한쪽/NNG+이/JKS 표면/NNG+적/XSN+으로/JKB+는/JX 지/VX+ㄴ/ETM 것/NNB+처럼/JKB 보이/VV+어도/EC 여론/NNG+이/JKS 그렇/VA+게/EC 생각/NNG+하/XSV+지/EC 않/VX+으면/EC 장기/NNG+적/XSN 관점/NNG+에서/JKB 승자/NNG+가/JKC 되/VV+ㄹ/ETM 수/NNB+도/JX 있/VA+다/EF+./SF
이/MM 때문/NNB+에/JKB 각/MM 정당/NNG+에서/JKB 벌어지/VV+는/ETM 싸움/NNG+은/JX 단순/NNG+히/EC 승자/NNG+와/JC 패자/NNG+를/JKO 가르/VV+는/ETM 싸움/NNG+이/JKC 아니/VCN+다/EF+./SF
```

##### ETRI
```text
이렇/VA+듯/EC 거대/NNG 양당/NNG+이/JKS 모두/MAG 소용돌이/NNG 속/NNG+에/JKB 진입하/VV+고/EC 있/VX+다/EF+./SF
각/MM 정당/NNG 내부/NNG+의/JKG 싸움/NNG+이/JKS 어떤/MM 결론/NNG+으로/JKB 끝나/VV+ㄹ지/EC+는/JX 아무/NP+도/JX 모르/VV+ㄴ다/EF+./SF
하지만/MAJ 이/MM 싸움/NNG+이/JKS 향후/NNG 정국/NNG+을/JKO 결정짓/VV+는/ETM 데/NNB 상당하/VA+ㄴ/ETM 의미/NNG+를/JKO 가지/VV+ㄹ/ETM 수/NNB 있/VA+다/EF+./SF
누구/NP+가/JKS 이기/VV+느냐는/ETM 중요하/VA+지/EC 않/VX+다/EF+./SF
한쪽/NNG+이/JKS 표면적/NNG+으로/JKB+는/JX 지/VV+ㄴ/ETM 것/NNB+처럼/JKB 보이/VV+어도/EC 여론/NNG+이/JKS 그렇/VA+게/EC 생각하/VV+지/EC 않/VX+으면/EC 장기적/NNG 관점/NNG+에서/JKB 승자/NNG+가/JKC 되/VV+ㄹ/ETM 수/NNB+도/JX 있/VA+다/EF+./SF
이/MM 때문/NNB+에/JKB 각/MM 정당/NNG+에서/JKB 벌어지/VV+는/ETM 싸움/NNG+은/JX 단순히/MAG 승자/NNG+와/JC 패자/NNG+를/JKO 가르/VV+는/ETM 싸움/NNG+이/JKC 아니/VCN+다/EF+./SF
```

### 문장 번호 #2

원본 문장:
> 그는 전날 트위터에 "검사님께서 '계폭(계정폭파)'이 무엇인지 그 개념을 제대로 알고 있지 못하다고 느꼈고 조사 과정 중 계폭 개념에 관해 설명하는데 많은 시간을 썼다"며 "심지어 '멘션'이 무엇인지도 제대로 이해하지 못하는 듯했다"는 등의 내용을 올렸다.
#### 어절 구분
##### KKMA
> 그는⎵전날⎵트위터에⎵"⎵검사님께서⎵'⎵계폭(⎵계정⎵폭파)'⎵이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못⎵하다고⎵느꼈고⎵조사⎵과정⎵중⎵계⎵폭⎵개념에⎵관해⎵설명하는데⎵많은⎵시간을⎵썼다⎵"며⎵"⎵심지어⎵'⎵멘⎵션'⎵이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯했다"⎵는⎵등의⎵내용을⎵올렸다.
##### Twitter
> 그는⎵전날⎵트위터에⎵"검사님께서⎵'계폭(계정폭파)'이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중⎵계폭⎵개념에⎵관해⎵설명하는데⎵많은⎵시간을⎵썼다"며⎵"심지어⎵'멘션'이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯했다"는⎵등의⎵내용을⎵올렸다.
##### Eunjeon
> 그는⎵전날⎵트위터에⎵"⎵검사님께서⎵'⎵계⎵폭⎵(⎵계정⎵폭파⎵)⎵'⎵이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중⎵계⎵폭⎵개념에⎵관해⎵설명하는⎵데⎵많은⎵시간을⎵썼다⎵"⎵며⎵"⎵심지어⎵'⎵멘션⎵'⎵이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯했다⎵"⎵는⎵등의⎵내용을⎵올렸다⎵.
##### KOMORAN
> 그는⎵전날⎵트위터에⎵"검사님께서⎵'계폭(계정폭파)'이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중⎵계폭⎵개념에⎵관하여⎵설명하는데⎵많은⎵시간을⎵썼다"이며⎵"심지어⎵'멘션'이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯하였다"는⎵등의⎵내용을⎵올렸다.
##### Rhino
> 그는⎵전날⎵트위터에⎵"⎵검사님께서⎵'⎵계폭⎵(⎵계정폭파⎵)⎵'⎵이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중⎵계폭⎵개념에⎵관해⎵설명하는데⎵많은⎵시간을⎵썼다⎵"⎵며⎵"⎵심지어⎵'⎵멘션⎵'⎵이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯했다⎵"⎵는⎵등의⎵내용을⎵올렸다⎵.
##### Arirang
> 그⎵는⎵전날⎵트위터⎵에⎵"⎵검사⎵님께서⎵'⎵계⎵폭⎵(⎵계정폭파⎵)⎵'⎵이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중 계폭⎵개념에⎵관해⎵설명하는데⎵많은⎵시간을⎵썼다⎵"⎵며⎵"⎵심지⎵어⎵'⎵멘션⎵'⎵이⎵무엇인⎵지도⎵제대로⎵이해하지⎵못하는⎵듯했다⎵"⎵는⎵등의⎵내용을⎵올렸다⎵.
##### Hannanum
> 그는⎵전날⎵트위터에⎵"검사님께서⎵'계폭(계정폭파)'이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중⎵계폭⎵개념에⎵관해⎵설명하는데⎵많은⎵시간을⎵썼다"며⎵"심지어⎵'멘션'이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯했다"는⎵등의⎵내용을⎵올렸다⎵.
##### Daon
> 그는⎵전날⎵트위터에⎵"검사님께서⎵'계폭(계정폭파)'이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중⎵계폭⎵개념에⎵관해⎵설명하는데⎵많은⎵시간을⎵썼다"며⎵"심지어⎵'멘션'이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯했다"는⎵등의⎵내용을⎵올렸다.
##### ETRI
> 그는⎵전날⎵트위터에⎵"검사님께서⎵'계폭(계정폭파)'이⎵무엇인지⎵그⎵개념을⎵제대로⎵알고⎵있지⎵못하다고⎵느꼈고⎵조사⎵과정⎵중⎵계폭⎵개념에⎵관해⎵설명하는데⎵많은⎵시간을⎵썼다"며⎵"심지어⎵'멘션'이⎵무엇인지도⎵제대로⎵이해하지⎵못하는⎵듯했다"는⎵등의⎵내용을⎵올렸다.
#### 품사 분석
##### KKMA
```text
그/NP+는/JX 전날/NNG 트위터/NNG+에/JKB "/SS 검사/NNG+님/XSN+께서/JKS '/SS 계폭/NF+(/SS 계정/NNG 폭파/NNG+)/SS+'/SS 이/MM 무엇/NP+이/VCP+ㄴ지/EC 그/MM 개념/NNG+을/JKO 제대로/MAG 알/VV+고/EC 있/VX+지/EC 못/MAG 하다/NNP+이/VCP+고/EC 느끼/VV+었/EP+고/EC 조사/NNG 과정/NNG 중/NNB 계/XSN 폭/NNG 개념/NNG+에/JKB 관하/VV+어/EC 설명/NNG+하/XSV+는데/EC 많/VA+은/ETM 시간/NNG+을/JKO 쓰/VV+었/EP+다/EC "/SS+며/JC "/SS 심지어/MAG '/SS 메/VV+ㄴ/ETM 션/NF+'/SS 이/MM 무엇/NP+이/VCP+ㄴ지/EC+도/JX 제대로/MAG 이해/NNG+하/XSV+지/EC 못/MAG+하/XSV+는/ETM 듯/NNB+하/XSA+었/EP+다/EC+"/SS 늘/VA+ㄴ/ETM 등/NNB+의/JKG 내용/NNG+을/JKO 올리/VV+었/EP+다/EF+./SF
```

##### Twitter
```text
그/NNG+는/JX 전날/NNG 트위터/NNG+에/JX "/SF+검사/NNG+님/XSO+께서/JX '/SF+계폭/NNG+(/SF+계정/NNG+폭파/NNG+)'/SF+이/NNG 무엇/NNG+인지/JX 그/NNG 개념/NNG+을/JX 제대로/NNG 알/NNG+고/JX 있지/VA 못/XPV+하다고/VV 느꼈고/VV 조사/NNG 과정/NNG 중/NNG 계폭/NNG 개념/NNG+에/JX 관해/NNG 설명/NNG+하는데/VV 많은/VA 시간/NNG+을/JX 썼다/VV+"/SF+며/NNG "/SF+심지어/NNG '/SF+멘션/NNG+'/SF+이/NNG 무엇/NNG+인지도/NNG 제대로/NNG 이해/NNG+하지/VV 못/XPV+하는/VV 듯/NNG+했다/VV+"/SF+는/VV 등/NNG+의/JX 내용/NNG+을/JX 올렸다/VV+./SF
```

##### Eunjeon
```text
그/NP+는/JX 전/NNG+날/NNG 트위터/NNP+에/JKB "/SW 검사/NNG+님/XSN+께서/JKS '/SW 계/XSN 폭/NNG (/SS 계정/NNG 폭파/NNG )/SS '/SW 이/JKS 무엇/NP+이/VCP+ᆫ지/EC 그/MM 개념/NNG+을/JKO 제대로/MAG 알/VV+고/EC 있/VX+지/EC 못하/VX+다고/EC 느끼/VV+었/EP+고/EC 조사/NNG 과정/NNG 중/NNB 계/NNG 폭/NNG 개념/NNG+에/JKB 관하/VV+아/EC 설명/NNG+하/XSV+는/ETM 데/NNB 많/VA+은/ETM 시간/NNG+을/JKO 쓰/VV+었/EP+다/EC "/SW 며/EC "/SW 심지어/MAG '/SW 멘션/NNP '/SW 이/JKS 무엇/NP+이/VCP+ᆫ지/EC+도/JX 제대로/MAG 이해/NNG+하/XSV+지/EC 못하/VX+는/ETM 듯/NNB+하/XSA+았/EP+다/EC "/SW 는/ETM 등/NNB+의/JKG 내용/NNG+을/JKO 올리/VV+었/EP+다/EF ./SF
```

##### KOMORAN
```text
그/NP+는/JX 전날/NNG 트위터/NNP+에/JKB "/SS+검사/NNG+님/XSN+께서/JKS '/SS+계/NNG+폭/NNG+(/SS+계정/NNP+폭파/NNG+)/SS+'/SS+이/MM 무엇/NP+이/VCP+ㄴ지/EC 그/MM 개념/NNG+을/JKO 제대로/MAG 알/VV+고/EC 있/VV+지/EC 못하/VX+다고/EC 느끼/VV+었/EP+고/EC 조사/NNG 과정/NNG 중/NNB 계/NNG+폭/NNG 개념/NNG+에/JKB 관하/VV+아/EC 설명/NNG+하/XSV+는데/EC 많/VA+은/ETM 시간/NNG+을/JKO 쓰/VV+었/EP+다/EC+"/SS+이/VCP+며/EC "/SS+심지어/MAG '/SS+멘/NNG+션/NNG+'/SS+이/MM 무엇/NP+이/VCP+ㄴ지/EC+도/JX 제대로/MAG 이해/NNG+하/XSV+지/EC 못하/VX+는/ETM 듯/NNB+하/XSA+었/EP+다/EC+"/SS+는/JX 등/NNB+의/JKG 내용/NNG+을/JKO 올리/VV+었/EP+다/EF+./SF
```

##### Rhino
```text
그/NP+는/JX 전날/NNG 트위터/NNP+에/JKB "/SS 검사/NNG+님/XSN+께서/JKS '/SS 계/NNG+폭/NNG (/SS 계정/NNG+폭파/NNG )/SS '/SS 이/VCP 무엇/NP+이/VCP+ㄴ지/EC 그/MM 개념/NNG+을/JKO 제대로/MAG 알/VV+고/EC 있/VX+지/EC 못하/VX+다고/EC 느끼/VV+었/EP+고/EC 조사/NNG 과정/NNG 중/NNB 계/NNG+폭/NNG 개념/NNG+에/JKB 관해/NNG 설명하/VV+는데/EC 많/VA+은/ETM 시간/NNG+을/JKO 쓰/VV+었/EP+다/EF "/SS 며/EC "/SS 심지어/MAG '/SS 메/VV+ㄴ/ETM+션/NNP '/SS 이/VCP 무엇/NP+인지도/NNG 제대로/MAG 이해하/VV+지/EC 못하/VX+는/JX 듯/NNB+하/XSV+았/EP+다/EF "/SS 는/JX 등/NNG+의/JKG 내용/NNG+을/JKO 올리/VV+었/EP+다/EF ./SF
```

##### Arirang
```text
그/MAG 는/NNG 전날/NNG 트위터/NNG 에/MAG "/SS 검사/NNG 님/NNG+께서/JX '/SS 계/NNG 폭/NNG (/SS 계정폭파/NNG )/SS '/SS 이/JX 무엇/NNG+이/XSV+ㄴ지/EF 그/NNG 개념/NNG+을/JX 제/NNG+대로/JX 알/VV+고/EF 있/VV+지/EF 못하/VV+다고/EF 느끼/VV+었/EP+고/EF 조사/NNG 과정/NNG 중 계폭/NNG 개념/NNG+에/JX 관해/NNG 설명/NNG+하/XSV+는데/EF 많/VV+은/EF 시간/NNG+을/JX 쓰/VV+었/EP+다/EF "/SS 며/JX "/SS 심지/NNG 어/NNG '/SS 멘션/NNG '/SS 이/NNG 무엇/NNG+이/XSV+ㄴ/EF 지도/NNG 제/NNG+대로/JX 이해/NNG+하/XSV+지/EF 못하/VV+는/EF 듯/NNG+하/XSV+었/EP+다/EF "/SS 는/NNG 등/NNG+의/JX 내용/NNG+을/JX 올리/VV+었/EP+다/EF ./NA
```

##### Hannanum
```text
그/NP+는/JX 전날/NNG 트위터/NNG+에/JKB "/SS+검사/NNG+님/NNG+께서/JKS '계폭(계정폭파)'/NNG+이/JKC 무엇/NP+이/VCP+ㄴ지/EC 그/MM 개념/NNG+을/JKO 제대로/MAG 알/VV+고/EC 있/VX+지/EC 못하/VX+다/EF+고/JKQ 느끼/VV+었/EP+고/EC 조사/NNG 과정/NNG 중/NNB 계폭/NNG 개념/NNG+에/JKB 관하/VV+어/EC 설명/NNG+하/XSV+ㄴ데/EC 많/VA+은/ETM 시간/NNG+을/JKO 썼다"며/NNG "/SS+심지어/MAG '멘션'/NNG+이/JKC 무엇/NP+이/VCP+ㄴ지/EC+도/JX 제대로/MAG 이해/NNG+하/XSV+지/EC 못하/VX+는/ETM 듯하/VX+었/EP+다/EF+"/SS+는/JX 등/NNM+의/JKG 내용/NNG+을/JKO 올리/VV+었/EP+다/EF ./SF
```

##### Daon
```text
그/NP+는/JX 전날/NNG 트위터/NNG+에/JKB "/SP+검사/NNG+님/XSN+께서/JKS '/SP+계폭/NNG+(/SP+계정/NNG+폭파/NNG+)/SP+'/SP+이/JKS 무엇/NP+이/VCP+ㄴ지/EC 그/MM 개념/NNG+을/JKO 제대로/MAG 알/VV+고/EC 있/VX+지/EC 못하/VX+다고/EC 느끼/VV+었/EP+고/EC 조사/NNG 과정/NNG 중/NNB 계폭/NNG 개념/NNG+에/JKB 관하/VV+어/EC 설명/NNG+하/XSV+는데/EC 많/VA+은/ETM 시간/NNG+을/JKO 쓰/VV+었/EP+다/EF+"/SP+며/EC "/SP+심지어/MAG '/SP+메/VV+ㄴ/ETM+션/NNG+'/SP+이/JKS 무엇/NP+이/VCP+ㄴ지/EC+도/JX 제대로/MAG 이해/NNG+하/XSV+지/EC 못하/VX+는/ETM 듯하/VX+었/EP+다/EF+"/SP+는/ETM 등/NNB+의/JKG 내용/NNG+을/JKO 올리/VV+었/EP+다/EF+./SF
```

##### ETRI
```text
그/NP+는/JX 전날/NNG 트위터/NNG+에/JKB "/SS+검사/NNG+님/XSN+께서/JKS '/SS+계폭/NNG+(/SS+계정/NNG+폭파/NNG+)/SS+'/SS+이/JKS 무엇/NP+이/VCP+ㄴ지/EC 그/MM 개념/NNG+을/JKO 제대로/MAG 알/VV+고/EC 있/VX+지/EC 못하/VX+다고/EC 느끼/VV+었/EP+고/EC 조사/NNG 과정/NNG 중/NNB 계폭/NNG 개념/NNG+에/JKB 관하/VV+어/EC 설명하/VV+는데/EC 많/VA+은/ETM 시간/NNG+을/JKO 쓰/VV+었/EP+다/EF+"/SS+며/EC "/SS+심지어/MAG '/SS+멘션/NNG+'/SS+이/JKS 무엇/NP+이/VCP+ㄴ지/EC+도/JX 제대로/MAG 이해하/VV+지/EC 못하/VX+는/ETM 듯하/VX+었/EP+다/EF+"/SS+는/JX 등/NNB+의/JKG 내용/NNG+을/JKO 올리/VV+었/EP+다/EF+./SF
```

### 문장 번호 #3

원본 문장:
> ‘존재감이 미약한 영남 다선들’ 부분 역시 논쟁 소지가 있다. 존재감이라는 것이 중앙정치 무대에서의 존재감을 의미하나, 지역구를 돌보느라 중앙정치에 잘 등장하지 않은 의원들이 여기에 해당된다면 논쟁은 얼마든지 불붙을 수 있다.
#### 어절 구분
##### KKMA
> ‘⎵존재감이⎵미약한⎵영남⎵다⎵선들’⎵부분⎵역시⎵논쟁⎵소지가⎵있다.
>
> 존재⎵감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재감을⎵의미하나,⎵지역구를⎵돌보느라⎵중앙정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다.
##### Twitter
> ‘존재감이⎵미약한⎵영남⎵다선들’⎵부분⎵역시⎵논쟁⎵소지가⎵있다.
>
> 존재감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재감을⎵의미하나,⎵지역구를⎵돌보느라⎵중앙정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다.
##### Eunjeon
> ‘⎵존재감이⎵미약한⎵영남⎵다선들⎵’⎵부분⎵역시⎵논쟁⎵소지가⎵있다⎵.
>
> 존재감이⎵라는⎵것이⎵중앙⎵정치⎵무대에서의⎵존재감을⎵의미⎵하나⎵,⎵지역구를⎵돌보느라⎵중앙⎵정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다⎵.
##### KOMORAN
> ‘존재감이⎵미약한⎵영남⎵다선들’⎵부분⎵역시⎵논쟁⎵소지가⎵있다.
>
> 존재감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재감을⎵의미하나,⎵지역구를⎵돌보느라⎵중앙정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마이든지⎵불붙을⎵수⎵있다.
##### Rhino
> ‘⎵존재감이⎵미약한⎵영남⎵다선들⎵’⎵부분⎵역시⎵논쟁⎵소지가⎵있다⎵.
>
> 존재감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재감을⎵의미하나⎵,⎵지역구를⎵돌보느라⎵중앙정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다⎵.
##### Arirang
> ‘⎵존재⎵감이⎵미약한⎵영남⎵다⎵선들⎵’⎵부분⎵역시⎵논쟁⎵소지가⎵있다⎵.
>
> 존재⎵감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재⎵감을⎵의미하나⎵,⎵지역⎵구를⎵돌보느라⎵중앙⎵정치에⎵잘⎵등장하지⎵않은⎵의원⎵들이⎵여기에⎵해당⎵된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다⎵.
##### Hannanum
> ‘존재감이⎵미약한⎵영남⎵다선들’⎵부분⎵역시⎵논쟁⎵소지가⎵있다⎵.
>
> 존재감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재감을⎵의미하나,⎵지역구를⎵돌보느라⎵중앙정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다⎵.
##### Daon
> ‘존재감이⎵미약한⎵영남⎵다선들’⎵부분⎵역시⎵논쟁⎵소지가⎵있다.
>
> 존재감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재감을⎵의미하나,⎵지역구를⎵돌보느라⎵중앙정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다.
##### ETRI
> ‘존재감이⎵미약한⎵영남⎵다선들’⎵부분⎵역시⎵논쟁⎵소지가⎵있다.
>
> 존재감이라는⎵것이⎵중앙정치⎵무대에서의⎵존재감을⎵의미하나,⎵지역구를⎵돌보느라⎵중앙정치에⎵잘⎵등장하지⎵않은⎵의원들이⎵여기에⎵해당된다면⎵논쟁은⎵얼마든지⎵불붙을⎵수⎵있다.
#### 품사 분석
##### KKMA
```text
‘/SS 존재/NNG+감/XSN+이/JKS 미약/XR+하/XSA+ㄴ/ETM 영남/NNG 다/MAG 서/VV+ㄴ들데/EC+’/SS 부분/NNG 역시/MAG 논쟁/NNG 소지/NNG+가/JKS 있/VV+다/EF+./SF
존재/NNG 감이/NNG+이/VCP+라는/ETM 것/NNB+이/JX 중앙/NNG+정치/NNG 무대/NNG+에서/JKB+의/JKG 존재/NNG+감/XSN+을/JKO 의미/NNG+하/XSV+나/EC+,/SP 지역구/NNG+를/JKO 돌보/VV+느라/EC 중앙/NNG+정치/NNG+에/JKB 잘/MAG 등장/NNG+하/XSV+지/EC 않/VX+은/ETM 의원/NNG+들/XSN+이/JKS 여기/NP+에/JKB 해당/NNG+되/XSV+ㄴ다면/EC 논쟁/NNG+은/JX 얼마든지/MAG 불붙/VV+을/ETM 수/NNB 있/VV+다/EF+./SF
```

##### Twitter
```text
‘/SL+존재/NNG+감/NNG+이/JX 미약/NNG+한/JX 영남/NNG 다/MAG+선/NNG+들/XSO+’/SF 부분/NNG 역시/NNG 논쟁/NNG 소지/NNG+가/JX 있다/VA+./SF
존재/NNG+감/NNG+이라는/JX 것/NNG+이/JX 중앙/NNG+정치/NNG 무대/NNG+에서의/JX 존재/NNG+감/NNG+을/JX 의미/NNG+하나/NNG+,/SF 지역구/NNG+를/JX 돌보느라/VV 중앙/NNG+정치/NNG+에/JX 잘/VV 등장/NNG+하지/VV 않은/VV 의원/NNG+들/XSO+이/JX 여기/NNG+에/JX 해당/NNG+된다면/VV 논쟁/NNG+은/JX 얼마/NNG+든지/JX 불/NNG+붙을/VV 수/NNG 있다/VA+./SF
```

##### Eunjeon
```text
‘/SW 존재/NNG+감/NNG+이/JKS 미약/XR+하/XSA+ᆫ/ETM 영남/NNP 다선/NNG+들/XSN ’/SW 부분/NNG 역시/MAJ 논쟁/NNG 소지/NNG+가/JKS 있/VA+다/EF ./SF
존재/NNG+감/NNG+이/VCP 라는/ETM 것/NNB+이/JKS 중앙/NNG 정치/NNG 무대/NNG+에서/JKB+의/JKG 존재/NNG+감/NNG+을/JKO 의미/NNG 하나/NR ,/SP 지역/NNG+구/NNG+를/JKO 돌보/VV+느라/EC 중앙/NNG 정치/NNG+에/JKB 잘/MAG 등장/NNG+하/XSV+지/EC 않/VX+은/ETM 의원/NNG+들/XSN+이/JKS 여기/NP+에/JKB 해당/NNG+되/XSV+ᆫ다면/EC 논쟁/NNG+은/JX 얼마/NNG+이/VCP+든지/EC 불붙/VV+을/ETM 수/NNB 있/VV+다/EF ./SF
```

##### KOMORAN
```text
‘/SS+존재/NNP+감/NNG+이/JKS 미약/XR+하/XSA+ㄴ/ETM 영남/NNP 다선/NNG+들/XSN+’/SS 부분/NNG 역시/MAJ 논쟁/NNG 소지/NNG+가/JKS 있/VX+다/EF+./SF
존재/NNG+감/NNG+이/VCP+라는/ETM 것/NNB+이/JKS 중앙/NNG+정치/NNG 무대/NNG+에서/JKB+의/JKG 존재/NNG+감/NNG+을/JKO 의미/NNG+하/XSV+나/EC+,/SP 지역구/NNG+를/JKO 돌보/VV+느라/EC 중앙/NNG+정치/NNG+에/JKB 잘/MAG 등장/NNG+하/XSV+지/EC 않/VX+은/ETM 의원/NNG+들/XSN+이/JKS 여기/NP+에/JKB 해당/NNG+되/XSV+ㄴ다면/EC 논쟁/NNG+은/JX 얼마/NNG+이/VCP+든지/EC 불붙/VV+을/ETM 수/NNB 있/VX+다/EF+./SF
```

##### Rhino
```text
‘/SS 존재감/NNG+이/JKS 미약/XR+하/VV+ㄴ/ETM 영남/NNP 다선/NNG+들/XSN ’/SS 부분/NNG 역시/MAG 논쟁/NNG 소지/NNG+가/JKS 있/VX+다/EF ./SF
존재감/NNG+이/VCP+라는/ETM 것/NNB+이/JKS 중앙/NNG+정치/NNG 무대/NNG+에서/JKB+의/JKG 존재감/NNG+을/JKO 의미하/VV+나/EC ,/SP 지역구/NNG+를/JKO 돌보/VV+느라/EC 중앙/NNG+정치/NNG+에/JKB 잘/MAG 등장하/VV+지/EC 않/VX+은/ETM 의원/NNG+들/XSN+이/JKS 여기/NP+에/JKB 해당되/VV+ㄴ다면/EC 논쟁/NNG+은/JX 얼마/NNG+든지/EC 불붙/VV+을/JKO 수/MM 있/VX+다/EF ./SF
```

##### Arirang
```text
‘/SS 존재/NNG 감/NNG+이/JX 미약/NNG+하/XSV+ㄴ/EF 영남/NNG 다/NNG 서/VV+ㄴ들/EF ’/SS 부분/NNG 역시/NNG 논쟁/NNG 소지/NNG+가/JX 있/VV+다/EF ./SF
존재/NNG 감/NNG+이/XSV+라는/EF 것/NNG+이/JX 중앙정치/NNG 무대/NNG+에서의/JX 존재/NNG 감/VV+을/EF 의미/NNG+하/XSV+나/EF ,/SP 지역/NNG 구르/VV+ㄹ/EF 돌보/VV+느라/EF 중앙/NNG 정치/NNG+에/JX 잘/NNG 등장/NNG+하/XSV+지/EF 않/VV+은/EF 의원/NNG 들/NNG+이/JX 여기/NNG+에/JX 해당/NNG 되/VV+ㄴ다면/EF 논쟁/NNG+은/JX 얼마/NNG+든지/JX 불붙/VV+을/EF 수/NNG 있/VV+다/EF ./NA
```

##### Hannanum
```text
‘존재감/NNG+이/JKC 미약/NNG+한/NNG 영남/NNG 다선들’/NNG 부분/NNG 역시/MAG 논쟁/NNG 소지/NNG+가/JKS 있/VX+다/EF ./SF
존재/NNG+감/NNG+이/VCP+라/EF+는/ETM 것/NNB+이/JKC 중앙정치/NNG 무대/NNG+에서/JKB+의/JKG 존재/NNG+감/NNG+을/JKO 의미/NNG+하/XSV+어/EC+나/VX+아/EC+,/SP 지역구/NNG+를/JKO 돌보/VV+느라/EC 중앙정치/NNG+에/JKB 잘/MAG 등장/NNG+하/XSV+지/EC 않/VX+은/ETM 의원/NNG+들/NNG+이/JKC 여기/NP+에/JKB 해당/NNG+되/XSV+ㄴ다면/EC 논쟁/NNG+은/JX 얼마/NNG+이/VCP+든지/EC 불붙/VV+을/ETM 수/NNB 있/VX+다/EF ./SF
```

##### Daon
```text
‘/SW+존재감/NNG+이/JKS 미약/NNG+하/XSA+ㄴ/ETM 영남/NNP 다선/NNG+들/XSN+’/SW 부분/NNG 역시/MAG 논쟁/NNG 소지/NNG+가/JKS 있/VA+다/EF+./SF
존재감/NNG+이/VCP+라는/ETM 것/NNB+이/JKS 중앙정치/NNG 무대/NNG+에서/JKB+의/JKG 존재감/NNG+을/JKO 의미/NNG+하/XSV+나/EC+,/SP 지역구/NNG+를/JKO 돌보/VV+느라/EC 중앙정치/NNG+에/JKB 잘/MAG 등장/NNG+하/XSV+지/EC 않/VX+은/ETM 의원/NNG+들/XSN+이/JKS 여기/NP+에/JKB 해당/NNG+되/XSV+ㄴ다면/EC 논쟁/NNG+은/JX 얼마/NP+이/VCP+든지/EC 불붙/VV+을/ETM 수/NNB 있/VA+다/EF+./SF
```

##### ETRI
```text
‘/SS+존재감/NNG+이/JKS 미약하/VA+ㄴ/ETM 영남/NNP 다선/NNG+들/XSN+’/SS 부분/NNG 역시/MAG 논쟁/NNG 소지/NNG+가/JKS 있/VA+다/EF+./SF
존재감/NNG+이/VCP+라는/ETM 것/NNB+이/JKS 중앙/NNG+정치/NNG 무대/NNG+에서/JKB+의/JKG 존재감/NNG+을/JKO 의미하/VV+나/EC+,/SP 지역구/NNG+를/JKO 돌보/VV+느라/EC 중앙/NNG+정치/NNG+에/JKB 잘/MAG 등장하/VV+지/EC 않/VX+은/ETM 의원/NNG+들/XSN+이/JKS 여기/NP+에/JKB 해당되/VV+ㄴ다면/EC 논쟁/NNG+은/JX 얼마/NNG+든지/JX 불붙/VV+을/ETM 수/NNB 있/VA+다/EF+./SF
```

### 문장 번호 #4

원본 문장:
> 현재 14석의 의석수를 갖고 있는 민주평화당 입장에서도 ‘제명’과 같은 중징계는 부담스러울 수밖에 없다. 민주평화당 당규상 징계는 ‘경고’ ‘당직자격정지’ ‘당원자격정지’ ‘제명’이다. 이미 이 의원이 원내수석부대표에서 사퇴해서 당직자격정지는 의미가 없다. 민주평화당이 ‘당원자격정지’ 징계를 내리자니 솜방망이 처벌이라는 여론의 비판이 두렵고, 제명 조치를 하자니 당세 약화가 우려되는 상황이다.
#### 어절 구분
##### KKMA
> 현재⎵14석의⎵의석수를⎵갖고⎵있는⎵민주평화당⎵입장에서도⎵‘⎵제명’⎵과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다.
>
> 민주평화당⎵당규상⎵징계는⎵‘⎵경고’⎵‘⎵당직자격정지’⎵‘⎵당원자격정지’⎵‘⎵제명’⎵이다.
>
> 이미⎵이⎵의원이⎵원내수석부대⎵표에서⎵사퇴해서⎵당직자격정지는⎵의미가⎵없다.
>
> 민주평화당이⎵‘⎵당원자격정지’⎵징계를⎵내리자⎵니⎵솜방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고,⎵제명⎵조치를⎵하자⎵니⎵당세⎵약화가⎵우려되는⎵상황이다.
##### Twitter
> 현재⎵14석의⎵의석수를⎵갖고⎵있는⎵민주평화당⎵입장에서도⎵‘제명’과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다.
>
> 민주평화당⎵당규상⎵징계는⎵‘경고’⎵‘당직자격정지’⎵‘당원자격정지’⎵‘제명’이다.
>
> 이미⎵이⎵의원이⎵원내수석부대표에서⎵사퇴해서⎵당직자격정지는⎵의미가⎵없다.
>
> 민주평화당이⎵‘당원자격정지’⎵징계를⎵내리자니⎵솜방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이다.
##### Eunjeon
> 현재⎵14⎵석의⎵의석수를⎵갖고⎵있는⎵민주⎵평화당⎵입장에서도⎵‘⎵제명⎵’⎵과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다⎵.
>
> 민주⎵평화당⎵당규상⎵징계는⎵‘⎵경고⎵’⎵‘⎵당직⎵자격정지⎵’⎵‘⎵당원⎵자격정지⎵’⎵‘⎵제명⎵’⎵이⎵다⎵.
>
> 이미⎵이⎵의원이⎵원내⎵수석⎵부대⎵표에서⎵사퇴해서⎵당직⎵자격정지는⎵의미가⎵없다⎵.
>
> 민주⎵평화당이⎵‘⎵당원⎵자격정지⎵’⎵징계를⎵내리자니⎵솜방망이⎵처벌이⎵라는⎵여론의⎵비판이⎵두렵고⎵,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이⎵다⎵.
##### KOMORAN
> 현재⎵14석의⎵의석수를⎵갖고⎵있는⎵민주평화당⎵입장에서도⎵‘제명’과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다.
>
> 민주평화당⎵당규상⎵징계는⎵‘경고’⎵‘당직자격정지’⎵‘당원자격정지’⎵‘제명’이다.
>
> 이미⎵이⎵의원이⎵원내수석부대표에서⎵사퇴하여서⎵당직자격정지는⎵의미가⎵없다.
>
> 민주평화당이⎵‘당원자격정지’⎵징계를⎵내리자니⎵솜방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이다.
##### Rhino
> 현재⎵14석의⎵의석수를⎵갖고⎵있는⎵민주평화당⎵입장에서도⎵‘⎵제명⎵’⎵과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다⎵.
>
> 민주평화당⎵당규상⎵징계는⎵‘⎵경고⎵’⎵‘⎵당직자격정지⎵’⎵‘⎵당원자격정지⎵’⎵‘⎵제명⎵’⎵이다⎵.
>
> 이미⎵이⎵의원이⎵원내수석부대표에서⎵사퇴해서⎵당직자격정지는⎵의미가⎵없다⎵.
>
> 민주평화당이⎵‘⎵당원자격정지⎵’⎵징계를⎵내리자니⎵솜방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고⎵,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이다⎵.
##### Arirang
> 현재 1⎵4석⎵의⎵의석⎵수를⎵갖고⎵있는⎵민주⎵평화⎵당⎵입장에서도⎵‘⎵제명⎵’⎵과⎵같은⎵중⎵징계는⎵부담스러울⎵수밖에⎵없다⎵.
>
> 민주⎵평화⎵당⎵당규⎵상⎵징계는⎵‘⎵경고⎵’⎵‘⎵당직자격⎵정지⎵’⎵‘⎵당원자격⎵정지⎵’⎵‘⎵제명⎵’⎵이다⎵.
>
> 이미⎵이⎵의원이⎵원내⎵수석부대⎵표에서⎵사퇴해서⎵당직자격⎵정지는⎵의미가⎵없다⎵.
>
> 민주⎵평화⎵당이⎵‘⎵당원⎵자격정지⎵’⎵징계를⎵내리⎵자니⎵솜⎵방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고⎵,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이다⎵.
##### Hannanum
> 현재⎵14석의⎵의석수를⎵갖고⎵있는⎵민주평화당⎵입장에서도⎵‘제명’과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다⎵.
>
> 민주평화당⎵당규상⎵징계는⎵‘경고’⎵‘당직자격정지’⎵‘당원자격정지’⎵‘제명’이다⎵.
>
> 이미⎵이⎵의원이⎵원내수석부대표에서⎵사퇴해서⎵당직자격정지는⎵의미가⎵없다⎵.
>
> 민주평화당이⎵‘당원자격정지’⎵징계를⎵내리자니⎵솜방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이다⎵.
##### Daon
> 현재⎵14석의⎵의석수를⎵갖고⎵있는⎵민주평화당⎵입장에서도⎵‘제명’과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다.
>
> 민주평화당⎵당규상⎵징계는⎵‘경고’⎵‘당직자격정지’⎵‘당원자격정지’⎵‘제명’이다.
>
> 이미⎵이⎵의원이⎵원내수석부대표에서⎵사퇴해서⎵당직자격정지는⎵의미가⎵없다.
>
> 민주평화당이⎵‘당원자격정지’⎵징계를⎵내리자니⎵솜방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이다.
##### ETRI
> 현재⎵14석의⎵의석수를⎵갖고⎵있는⎵민주평화당⎵입장에서도⎵‘제명’과⎵같은⎵중징계는⎵부담스러울⎵수밖에⎵없다.
>
> 민주평화당⎵당규상⎵징계는⎵‘경고’⎵‘당직자격정지’⎵‘당원자격정지’⎵‘제명’이다.
>
> 이미⎵이⎵의원이⎵원내수석부대표에서⎵사퇴해서⎵당직자격정지는⎵의미가⎵없다.
>
> 민주평화당이⎵‘당원자격정지’⎵징계를⎵내리자니⎵솜방망이⎵처벌이라는⎵여론의⎵비판이⎵두렵고,⎵제명⎵조치를⎵하자니⎵당세⎵약화가⎵우려되는⎵상황이다.
#### 품사 분석
##### KKMA
```text
현재/NNG 14/NR+석/NNM+의/JKG 의석수/NNG+를/JKO 갖/VV+고/EC 있/VX+는/ETM 민주/NNG+평화/NNG+당/XSN 입장/NNG+에서/JKB+도/JX ‘/SS 제명/NNG+’/SS 과/JC 같/VA+은/ETM 중징/NNG+계/XSN+는/JX 부담/NNG+스럽/XSA+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
민주/NNG+평화/NNG+당/XSN 당규/NNG+상/XSN 징/NNG+계/XSN+는/JX ‘/SS 경고/NNG+’/SS ‘/SS 당직/NNG+자격/NNG+정지/NNG+’/SS ‘/SS 당원/NNG+자격/NNG+정지/NNG+’/SS ‘/SS 제명/NNG+’/SS 이/VCP+다/EF+./SF
이미/MAG 이/MM 의원/NNG+이/JKS 원내/NNG+수석/NNG+부대/NNG 표/NNG+에서/JKB 사퇴/NNG+하/XSV+어서/EC 당직/NNG+자격/NNG+정지/NNG+는/JX 의미/NNG+가/JKS 없/VA+다/EF+./SF
민주/NNG+평화/NNG+당/XSN+이/JKS ‘/SS 당원/NNG+자격/NNG+정지/NNG+’/SS 징/NNG+계/XSN+를/JKO 내리/VV+자/EC 니/NP 솜방망이/NNG 처벌/NNG+이/VCP+라는/ETM 여론/NNG+의/JKG 비판/NNG+이/JKS 두렵/VA+고/EC+,/SP 제명/NNG 조치/NNG+를/JKO 하/VV+자/EC 니/NP 당세/NNG 약화/NNG+가/JKS 우려/NNG+되/XSV+는/ETM 상황/NNG+이/VCP+다/EF+./SF
```

##### Twitter
```text
현재/NNG 14/NR+석/NNG+의/JX 의/NNG+석수/NNG+를/JX 갖고/VV 있는/VA 민주/NNG+평화/NNG+당/XSO 입장/NNG+에서도/JX ‘/SL+제명/NNG+’/SF+과/NNG 같은/VA 중/NNG+징계/NNG+는/JX 부담스러울/VA 수/NNG+밖에/JX 없다/VA+./SF
민주/NNG+평화/NNG+당/XSO 당/MM+규/NNG+상/XSO 징계/NNG+는/JX ‘/SL+경고/NNG+’/SF ‘/SL+당직/NNG+자격정지/NNG+’/SF ‘/SL+당원/NNG+자격정지/NNG+’/SF ‘/SL+제명/NNG+’/SF+이다/JX+./SF
이미/MAG 이/NNG 의원/NNG+이/JX 원/MM+내/MM+수/MM+석/MM+부/NNG+대표/NNG+에서/JX 사퇴/NNG+해서/VV 당직/NNG+자격정지/NNG+는/JX 의미/NNG+가/JX 없다/VA+./SF
민주/NNG+평화/NNG+당/XSO+이/JX ‘/SL+당원/NNG+자격정지/NNG+’/SF 징계/NNG+를/JX 내/MM+리자/NNG+니/JX 솜방망이/NNG 처벌/NNG+이라는/JX 여론/NNG+의/JX 비판/NNG+이/JX 두렵고/VA+,/SF 제명/NNG 조치/NNG+를/JX 하자/NNG+니/JX 당세/NNG 약화/NNG+가/JX 우려/NNG+되는/VV 상황/NNG+이다/JX+./SF
```

##### Eunjeon
```text
현재/MAG 14/SN 석/NNM+의/JKG 의석/NNG+수/NNG+를/JKO 갖/VV+고/EC 있/VX+는/ETM 민주/NNG 평화/NNG+당/XSN 입장/NNG+에서/JKB+도/JX ‘/SW 제명/NNG ’/SW 과/JKB 같/VA+은/ETM 중/NNG+징계/NNG+는/JX 부담/NNG+스럽/XSA+ᆯ/ETM 수/NNB+밖에/JX 없/VA+다/EF ./SF
민주/NNG 평화/NNG+당/XSN 당규/NNG+상/XSN 징계/NNG+는/JX ‘/SW 경고/NNG ’/SW ‘/SW 당직/NNG 자격/NNG+정지/NNG ’/SW ‘/SW 당원/NNP 자격/NNG+정지/NNG ’/SW ‘/SW 제명/NNG ’/SW 이/VCP 다/EF ./SF
이미/MAG 이/MM 의원/NNG+이/JKS 원내/NNG 수석/NNG 부대/NNG 표/NNG+에서/JKB 사퇴/NNG+하/XSV+아서/EC 당직/NNG 자격/NNG+정지/NNG+는/JX 의미/NNG+가/JKS 없/VA+다/EF ./SF
민주/NNG 평화/NNG+당/XSN+이/JKS ‘/SW 당원/NNP 자격/NNG+정지/NNG ’/SW 징계/NNG+를/JKO 내리/VV+자니/EC 솜/NNG+방망이/NNG 처벌/NNG+이/VCP 라는/ETM 여론/NNG+의/JKG 비판/NNG+이/JKS 두렵/VA+고/EC ,/SP 제명/NNG 조치/NNG+를/JKO 하/VV+자니/EC 당세/NNG 약화/NNG+가/JKS 우려/NNG+되/XSV+는/ETM 상황/NNG+이/VCP 다/EF ./SF
```

##### KOMORAN
```text
현재/MAG 14/SN+석/NNB+의/JKG 의석수/NNG+를/JKO 갖/VV+고/EC 있/VV+는/ETM 민주/NNP+평화/NNP+당/NNP 입장/NNG+에서/JKB+도/JX ‘/SS+제명/NNP+’/SS+과/JC 같/VA+은/ETM 중징계/NNG+는/JX 부담/NNG+스럽/XSA+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
민주/NNP+평화/NNP+당/NNP 당규/NNG+상/XSN 징계/NNG+는/JX ‘/SS+경고/NNG+’/SS ‘/SS+당직/NNG+자격정지/NNP+’/SS ‘/SS+당원/NNG+자격정지/NNP+’/SS ‘/SS+제명/NNP+’/SS+이/VCP+다/EF+./SF
이미/MAG 이/MM 의원/NNG+이/JKS 원내/NNG+수석/NNP+부대표/NNG+에서/JKB 사퇴/NNG+하/XSV+아서/EC 당직/NNG+자격정지/NNP+는/JX 의미/NNG+가/JKS 없/VA+다/EF+./SF
민주/NNP+평화/NNP+당/NNP+이/JKS ‘/SS+당원/NNG+자격정지/NNP+’/SS 징계/NNG+를/JKO 내리/VV+자니/EC 솜방망이/NNP 처벌/NNG+이/VCP+라는/ETM 여론/NNG+의/JKG 비판/NNG+이/JKS 두렵/VA+고/EC+,/SP 제명/NNP 조치/NNG+를/JKO 하/VV+자니/EC 당/NNG+세/NNB 약화/NNG+가/JKS 우려/NNG+되/XSV+는/ETM 상황/NNG+이/VCP+다/EF+./SF
```

##### Rhino
```text
현재/MAG 14/SN+석/NNB+의/JKG 의석수/NNG+를/JKO 갖/VV+고/EC 있/VX+는/ETM 민주/NNG+평화/NNG+당/XSN 입장/NNG+에서/JKB+도/JX ‘/SS 제명/NNG ’/SS 과/NNG 같/VA+은/ETM 중징계/NNG+는/JX 부담/NNG+스럽/XSA+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF ./SF
민주/NNG+평화/NNG+당/XSN 당규/NNG+상/XSN 징계/NNG+는/JX ‘/SS 경고/NNG ’/SS ‘/SS 당직자/NNG+격정/NNG+지/EC ’/SS ‘/SS 당원/NNG+자격/NNG+정지/NNG ’/SS ‘/SS 제명/NNG ’/SS 이다/VV ./SF
이미/MAG 이/MM 의원/NNG+이/JKS 원내/NNG+수석/NNG+부대표/NNG+에서/JKB 사퇴하/VV+아서/EC 당직자/NNG+격정/NNG+지/VX+는/JX 의미/NNG+가/JKS 없/VA+다/EF ./SF
민주/NNG+평화/NNG+당/XSN+이/JKS ‘/SS 당원/NNG+자격/NNG+정지/NNG ’/SS 징계/NNG+를/JKO 내리/VV+자니/EC 솜방망이/NNG 처벌/NNG+이/VCP+라는/ETM 여론/NNG+의/JKG 비판/NNG+이/JKS 두렵/VA+고/EC ,/SP 제명/NNG 조치/NNG+를/JKO 하/VV+자/EF+니/EC 당세/NNG 약화/NNG+가/JKS 우려되/VV+는/ETM 상황/NNG+이다/EF ./SF
```

##### Arirang
```text
현재 1/NNG 4석/NNG 의/NNG 의석/NNG 수/NNG+를/JX 갖/VV+고/EF 있/VV+는/EF 민주/NNG 평화/NNG 당/NNG 입장/NNG+에서도/JX ‘/SS 제명/NNG ’/SS 과/NNG 같/VV+은/EF 중/NNG 징계/NNG+는/JX 부담/NNG+스럽/XSV+ㄹ/EF 수/NNG+밖에/JX 없/VV+다/EF ./SF
민주/NNG 평화/NNG 당/NNG 당규/NNG 상/NNG 징계/NNG+는/JX ‘/SS 경고/NNG ’/SS ‘/SS 당직자격/NNG 정지/NNG ’/SS ‘/SS 당원자격/NNG 정지/NNG ’/SS ‘/SS 제명/NNG ’/SS 이/NNG+이/XSV+다/EF ./SF
이미/NNG 이/NNG 의원/NNG+이/JX 원내/NNG 수석부대/NNG 표/NNG+에서/JX 사퇴/NNG+하/XSV+어서/EF 당직자격/NNG 정지/NNG+는/JX 의미/NNG+가/JX 없/VV+다/EF ./SF
민주/NNG 평화/NNG 당/NNG+이/JX ‘/SS 당원/NNG 자격정지/NNG ’/SS 징계/NNG+를/JX 내/VV+리/EF 자/VV+니/EF 솜/NNG 방망이/NNG 처벌/NNG+이/XSV+라는/EF 여론/NNG+의/JX 비판/NNG+이/JX 두렵/VV+고/EF ,/SP 제명/NNG 조치/NNG+를/JX 하자/NNG+니/JX 당세/NNG 약화/NNG+가/JX 우려/NNG+되/XSV+는/EF 상황/NNG+이/XSV+다/EF ./NA
```

##### Hannanum
```text
현재/MAG 14/NR+석/NNM+의/JKG 의석수/NNG+를/JKO 갖/VV+고/EC 있/VX+는/ETM 민주/NNG+평화/NNG+당/NNG 입장/NNG+에서/JKB+도/JX ‘제명’/NNG+과/JC 같/VA+은/ETM 중징계/NNG+는/JX 부담/NNG+스럽/XSA+을/ETM 수/NNB+밖에/JX 없/VA+다/EF ./SF
민주/NNG+평화/NNG+당/NNG 당규/NNG+상/NNG 징계/NNG+는/JX ‘경고’/NNG ‘당직자격정지’/NNG ‘당원자격정지’/NNG ‘제명’/NNG+이/VCP+다/EF ./SF
이미/MAG 이/MM 의원/NNG+이/JKC 원내/NNG+수석/NNG+부대표/NNG+에서/JKB 사퇴/NNG+하/XSV+어서/EC 당직/NNG+자격정지/NNG+는/JX 의미/NNG+가/JKC 없/VA+다/EF ./SF
민주/NNG+평화/NNG+당/NNG+이/JKC ‘당원자격정지’/NNG 징계/NNG+를/JKO 내리/VV+자니/EC 솜방망이/NNG 처벌/NNG+이/VCP+라/EF+는/ETM 여론/NNG+의/JKG 비판/NNG+이/JKC 두렵/VA+고/EC+,/SP 제명/NNG 조/NR+치/NNM+를/JKO 하/VV+자니/EC 당세/NNG 약화/NNG+가/JKC 우/NNG+이/VCP+리/EP+어/EC+되/VX+는/ETM 상황/NNG+이/VCP+다/EF ./SF
```

##### Daon
```text
현재/MAG 14/SN+석/NNB+의/JKG 의석수/NNG+를/JKO 갖/VV+고/EC 있/VX+는/ETM 민주평화/NNG+당/XSN 입장/NNG+에서/JKB+도/JX ‘/SW+제명/NNG+’/SW+과/JKB 같/VA+은/ETM 중징계/NNG+는/JX 부담/NNG+스럽/XSA+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
민주평화/NNG+당/XSN 당규/NNG+상/XSN 징계/NNG+는/JX ‘/SW+경고/NNG+’/SW ‘/SW+당직/NNG+자격정지/NNG+’/SW ‘/SW+당원/NNG+자격정지/NNG+’/SW ‘/SW+제명/NNG+’/SW+이/VCP+다/EF+./SF
이미/MAG 이/MM 의원/NNG+이/JKS 원내/NNG+수석/NNG+부대표/NNG+에서/JKB 사퇴/NNG+하/XSV+어서/EC 당직/NNG+자격정지/NNG+는/JX 의미/NNG+가/JKS 없/VA+다/EF+./SF
민주평화/NNG+당/XSN+이/JKS ‘/SW+당원/NNG+자격정지/NNG+’/SW 징계/NNG+를/JKO 내리/VV+자니/EC 솜방망이/NNG 처벌/NNG+이/VCP+라는/ETM 여론/NNG+의/JKG 비판/NNG+이/JKS 두렵/VA+고/EC+,/SP 제명/NNG 조치/NNG+를/JKO 하/VV+자니/EC 당세/NNG 약화/NNG+가/JKS 우려/NNG+되/XSV+는/ETM 상황/NNG+이/VCP+다/EF+./SF
```

##### ETRI
```text
현재/MAG 14/SN+석/NNB+의/JKG 의석수/NNG+를/JKO 갖/VV+고/EC 있/VX+는/ETM 민주/NNG+평화/NNG+당/XSN 입장/NNG+에서/JKB+도/JX ‘/SS+제명/NNG+’/SS+과/JKB 같/VA+은/ETM 중징계/NNG+는/JX 부담스럽/VA+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
민주/NNG+평화/NNG+당/NNG 당규/NNG+상/XSN 징계/NNG+는/JX ‘/SS+경고/NNG+’/SS ‘/SS+당직자/NNG+격/NNB+정지/NNG+’/SS ‘/SS+당원/NNG+자격정지/NNG+’/SS ‘/SS+제명/NNG+’/SS+이/VCP+다/EF+./SF
이미/MAG 이/NNP 의원/NNG+이/JKS 원내/NNG+수석/NNG+부/XSN+대표/NNG+에서/JKB 사퇴하/VV+어서/EC 당직자/NNG+격/NNB+정지/NNG+는/JX 의미/NNG+가/JKS 없/VA+다/EF+./SF
민주/NNG+평화/NNG+당/NNG+이/JKS ‘/SS+당원/NNG+자격정지/NNG+’/SS 징계/NNG+를/JKO 내리/VV+자니/EC 솜방망이/NNG 처벌/NNG+이/VCP+라는/ETM 여론/NNG+의/JKG 비판/NNG+이/JKS 두렵/VA+고/EC+,/SP 제명/NNG 조치/NNG+를/JKO 하/VV+자니/EC 당세/NNG 약화/NNG+가/JKS 우려되/VV+는/ETM 상황/NNG+이/VCP+다/EF+./SF
```

### 문장 번호 #5

원본 문장:
> (수원=연합뉴스) 류수현 기자 = 이른바 '혜경궁 김씨(@08__hkkim)' 트위터 계정 소유주로 이재명 경기지사의 부인 김혜경씨를 지목해 고발한 이정렬 변호사가 25일 고발 대리인에서 물러나겠다고 밝혔다.
#### 어절 구분
##### KKMA
> (⎵수원=⎵연합⎵뉴스)⎵류수현⎵기자⎵=⎵이른바⎵'⎵혜경궁⎵김⎵씨(⎵@08⎵__hkkim)'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기지사의⎵부인⎵김⎵혜경⎵씨를⎵지목해⎵고발한⎵이⎵정렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고
>
> 밝혔다.
##### Twitter
> (수원=연합뉴스)⎵류수현⎵기자⎵=⎵이른바⎵'혜경궁⎵김씨(@08__hkkim)'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기지사의⎵부인⎵김혜경씨를⎵지목해⎵고발한⎵이정렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다.
##### Eunjeon
> (⎵수원⎵=⎵연합뉴스⎵)⎵류수현⎵기자⎵=⎵이른바⎵'⎵혜경궁⎵김⎵씨⎵(⎵@⎵08⎵_⎵_⎵hkkim⎵)⎵'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기⎵지사의⎵부인⎵김혜경⎵씨를⎵지목해⎵고발한⎵이정렬⎵변호사가⎵25⎵일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다⎵.
##### KOMORAN
> (수원=연합뉴스)⎵류수현⎵기자⎵=⎵이른바⎵'혜경궁⎵김씨(@08__hkkim)'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기지사의⎵부인⎵김혜경씨를⎵지목하여⎵고발한⎵이정렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다.
##### Rhino
> (⎵수원⎵=⎵연합뉴스⎵)⎵류수현⎵기자⎵=⎵이른바⎵'⎵혜경궁⎵김씨⎵(⎵@⎵08⎵_⎵_⎵hkkim⎵)⎵'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기지사의⎵부인⎵김혜경씨를⎵지목해⎵고발한⎵이정렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다⎵.
##### Arirang
> (⎵수⎵원=⎵연합⎵뉴스⎵)⎵류수현⎵기자 =⎵이른바⎵'⎵혜경궁⎵김씨⎵(⎵@08__⎵hkkim⎵)⎵'⎵트위터⎵계정⎵소유주로⎵이재⎵명⎵경기⎵지사의⎵부인⎵김혜경⎵씨를⎵지목해⎵고발한⎵이정⎵렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다⎵.
##### Hannanum
> (수원=연합뉴스)⎵류수현⎵기자⎵=⎵이른바⎵'혜경궁⎵김씨(@08__hkkim)'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기지사의⎵부인⎵김혜경씨를⎵지목해⎵고발한⎵이정렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다⎵.
##### Daon
> (수원=연합뉴스)⎵류수현⎵기자⎵=⎵이른바⎵'혜경궁⎵김씨(@08__hkkim)'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기지사의⎵부인⎵김혜경씨를⎵지목해⎵고발한⎵이정렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다.
##### ETRI
> (수원=연합뉴스)⎵류수현⎵기자⎵=⎵이른바⎵'혜경궁⎵김씨(@08__hkkim)'⎵트위터⎵계정⎵소유주로⎵이재명⎵경기지사의⎵부인⎵김혜경씨를⎵지목해⎵고발한⎵이정렬⎵변호사가⎵25일⎵고발⎵대리인에서⎵물러나겠다고⎵밝혔다.
#### 품사 분석
##### KKMA
```text
(/SS 수원/NNG+=/SW 연합/NNG 뉴스/NNG+)/SS 류수/NF+혀/XSV+ㄴ/ETM 기자/NNG =/SW 이른바/MAG '/SS 혜경궁/NNG 김/NNB 씨/NNG+(/SS @/SW+08/NR __/SW+hkkim/SL+)/SS+'/SS 트위터/NNG 계정/NNG 소유주/NNG+로/JKB 이재명/NNG 경기/NNG+지사/NNG+의/JKG 부이/VV+ㄴ/ETM 김/NNB 혜/NF+경/XSN 씨/NNG+를/JKO 지목/NNG+하/XSV+어/EC 고발/NNG+하/XSV+ㄴ/ETM 이/NNG 정렬/NNG 변호사/NNG+가/JKS 25/NR+일/NNM 고발/NNG 대리인/NNG+에서/JKB 물러나/VV+겠/EP+다고/EF
밝히/VV+었/EP+다/EF+./SF
```

##### Twitter
```text
(/SF+수원/NNG+=/SF+연합뉴스/NNG+)/SF 류수현/NNG 기자/NNG =/SF 이른바/MAG '/SF+혜경궁/NNG 김씨/NNG+(@08__hkkim/SW+)'/SF 트위터/NNG 계정/NNG 소/MM+유주/NNG+로/JX 이재명/NNG 경/MM+기/MM+지사/NNG+의/JX 부인/NNG 김혜경/NNG+씨/XSO+를/JX 지목/NNG+해/VV 고발/NNG+한/JX 이/MM+정렬/NNG 변호사/NNG+가/JX 25일/NR 고발/NNG 대리인/NNG+에서/JX 물러나겠다고/VV 밝혔다/VV+./SF
```

##### Eunjeon
```text
(/SS 수원/NNP =/SW 연합/NNG+뉴스/NNG )/SS 류수현/NNP 기자/NNG =/SW 이른바/MAJ '/SW 혜경궁/NNP 김/NNP 씨/NNB (/SS @/SW 08/SN _/SW _/SW hkkim/SL )/SS '/SW 트위터/NNP 계정/NNG 소유/NNG+주/NNG+로/JKB 이재명/NNP 경기/NNG 지사/NNG+의/JKG 부인/NNG 김혜경/NNP 씨/NNB+를/JKO 지목/NNG+하/XSV+아/EC 고발/NNG+하/XSA+ᆫ/ETM 이정렬/NNP 변호/NNG+사/NNG+가/JKS 25/SN 일/NNM 고발/NNG 대리/NNG+인/NNG+에서/JKB 물러나/VV+겠/EP+다고/EC 밝히/VV+었/EP+다/EF ./SF
```

##### KOMORAN
```text
(/SS+수원/NNP+=/SW+연합뉴스/NNP+)/SS 류/NNP+수현/NNP 기자/NNG =/SW 이른바/MAJ '/SS+혜경/NNP+궁/NNG 김/NNP+씨/NNB+(/SS+@/SW+08/SN+_/SW+_/SW+hkkim/SL+)/SS+'/SS 트위터/NNP 계정/NNP 소유주/NNG+로/JKB 이재명/NNP 경기/NNP+지사/NNP+의/JKG 부인/NNG 김혜경/NNP+씨/NNB+를/JKO 지목/NNG+하/XSV+아/EC 고발/NNG+하/XSV+ㄴ/ETM 이정렬/NNP 변호사/NNG+가/JKS 25/SN+일/NNB 고발/NNP 대리인/NNP+에서/JKB 물러나/VV+겠/EP+다고/EC 밝히/VV+었/EP+다/EF+./SF
```

##### Rhino
```text
(/SS 수원/NNP =/SW 연합뉴스/NNP )/SS 류/NNP+수현/NNP 기자/NNG =/SW 이른바/MAG '/SS 혜경궁/NNP 김/NNP+씨/NNB (/SS @/SW 08/SN _/SO _/SO hkkim/SL )/SS '/SS 트위터/NNP 계정/NNG 소유주/NNG+로/JKB 이재명/NNP 경기/NNP+지사/NNG+의/JKG 부인/NNG 김/NNG+혜경/NNP+씨/NNB+를/JKO 지목하/VV+아/EC 고발하/VV+ㄴ/ETM 이정렬/NNP 변호사/NNG+가/JKS 25/SN+일/NNB 고발/NNG 대리인/NNG+에서/JKB 물러나/VV+겠/EP+다고/EC 밝히/VV+었/EP+다/EF ./SF
```

##### Arirang
```text
(/SS 수/NNG 원=/NNG 연합/NNG 뉴스/NNG )/SS 류수현/NNG 기자 =/NNG 이르/VV+ㄴ바/EF '/SS 혜경궁/NNG 김씨/NNG (/SS @08__/NNG hkkim/NNG )/SS '/SS 트위터/NNG 계정/NNG 소유주/NNG+로/JX 이재/NNG 명/NNG 경기/NNG 지사/NNG+의/JX 부인/NNG 김혜경/NNG 씨/NNG+를/JX 지목/NNG+하/XSV+어/EF 고발/NNG+하/XSV+ㄴ/EF 이정/NNG 렬/NNG 변호사/NNG+가/JX 25일/NNG 고발/NNG 대리인/NNG+에서/JX 물러나/VV+겠/EP+다고/EF 밝히/VV+었/EP+다/EF ./NA
```

##### Hannanum
```text
(수원=연합뉴스)/NNG 류수현/NNG 기/NNG+이/VCP+자/EC =/SW 이른바/MAJ '혜경궁/NNG 김씨(@08__hkkim)'/NNG 트위터/NNG 계정/NNG 소유주/NNG+로/JKB 이재/NNG+명/NNG 경기/NNG+지사/NNG+의/JKG 부인/NNG 김혜경씨/NNG+를/JKO 지목/NNG+하/XSV+어/EC 고발/NNG+하/XSV+ㄴ/ETM 이정렬/NNG 변호사/NNG+가/JKC 25/NR+일/NNM 고발/NNG 대리인/NNG+에서/JKB 무르/VV+어/EC+나/VX+겠/EP+다/EF+고/JKQ 밝히/VV+었/EP+다/EF ./SF
```

##### Daon
```text
(/SP+수원/NNP+=/SP+연합뉴스/NNP+)/SP 류수/NNG+현/MM 기자/NNG =/SP 이른바/MAG '/SP+혜경궁/NNP 김/NNP+씨/NNB+(/SP+@/SP+08/SN+__/SW+hkkim/SL+)/SP+'/SP 트위터/NNG 계정/NNG 소유주/NNG+로/JKB 이재명/NNP 경기지사/NNG+의/JKG 부인/NNG 김혜경/NNP+씨/NNB+를/JKO 지목/NNG+하/XSV+어/EC 고발/NNG+하/XSV+ㄴ/ETM 이정렬/NNP 변호사/NNG+가/JKS 25/SN+일/NNB 고발/NNG 대리인/NNG+에서/JKB 물러나/VV+겠/EP+다고/EC 밝히/VV+었/EP+다/EF+./SF
```

##### ETRI
```text
(/SS+수원/NNP+=/SW+연합/NNG+뉴스/NNG+)/SS 류수현/NNP 기자/NNG =/SW 이른바/MAG '/SS+혜경/NNG+궁/NNG 김/NNP+씨/NNB+(/SS+@/SW+08/SN+_/SW+_/SW+hkkim/SL+)/SS+'/SS 트위터/NNG 계정/NNG 소유주/NNG+로/JKB 이재명/NNP 경기/NNG+지사/NNG+의/JKG 부인/NNG 김혜경/NNG+씨/NNB+를/JKO 지목하/VV+어/EC 고발하/VV+ㄴ/ETM 이정렬/NNP 변호사/NNG+가/JKS 25/SN+일/NNB 고발/NNG 대리인/NNG+에서/JKB 물러나/VV+겠/EP+다고/EC 밝히/VV+었/EP+다/EF+./SF
```

### 문장 번호 #6

원본 문장:
> 그런데 프레임 싸움이 되면 법정 싸움에서 이기더라도 궁극적으로 진 셈이 될 수 있다. 이긴다 해도 프레임에서 밀려버리면 이겼다는 의미가 상쇄된다. 그런 점에서 보자면 이재명 지사와 주류 간 투쟁은 간단치 않은 싸움이 될 수밖에 없다.
#### 어절 구분
##### KKMA
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다.
>
> 이긴다⎵해도⎵프레임에서⎵밀려⎵버리면⎵이겼다는⎵의미가⎵상쇄된다.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단치⎵않은⎵싸움이⎵될⎵수밖에⎵없다.
##### Twitter
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다.
>
> 이긴다⎵해도⎵프레임에서⎵밀려버리면⎵이겼다는⎵의미가⎵상쇄된다.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단치⎵않은⎵싸움이⎵될⎵수밖에⎵없다.
##### Eunjeon
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다⎵.
>
> 이긴다⎵해도⎵프레임에서⎵밀려⎵버리면⎵이겼다는⎵의미가⎵상쇄된다⎵.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단치⎵않은⎵싸움이⎵될⎵수밖에⎵없다⎵.
##### KOMORAN
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다.
>
> 이긴다⎵하여도⎵프레임에서⎵밀려버리면⎵이겼다는⎵의미가⎵상쇄된다.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단하지⎵않은⎵싸움이⎵될⎵수밖에⎵없다.
##### Rhino
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다⎵.
>
> 이긴다⎵해도⎵프레임에서⎵밀려버리면⎵이겼다는⎵의미가⎵상쇄된다⎵.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단치⎵않은⎵싸움이⎵될⎵수밖에⎵없다⎵.
##### Arirang
> 그⎵런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극⎵적으로⎵진⎵셈이⎵될 수⎵있다⎵.
>
> 이긴다⎵해도⎵프레임에서⎵밀려⎵버리면⎵이겼다는⎵의미가⎵상쇄된다⎵.
>
> 그런⎵점에서⎵보자면⎵이재⎵명⎵지사와⎵주류⎵간⎵투쟁은⎵간단⎵치⎵않은⎵싸움이⎵될⎵수밖에⎵없다⎵.
##### Hannanum
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다⎵.
>
> 이긴다⎵해도⎵프레임에서⎵밀려버리면⎵이겼다는⎵의미가⎵상쇄된다⎵.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단치⎵않은⎵싸움이⎵될⎵수밖에⎵없다⎵.
##### Daon
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다.
>
> 이긴다⎵해도⎵프레임에서⎵밀려버리면⎵이겼다는⎵의미가⎵상쇄된다.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단치⎵않은⎵싸움이⎵될⎵수밖에⎵없다.
##### ETRI
> 그런데⎵프레임⎵싸움이⎵되면⎵법정⎵싸움에서⎵이기더라도⎵궁극적으로⎵진⎵셈이⎵될⎵수⎵있다.
>
> 이긴다⎵해도⎵프레임에서⎵밀려버리면⎵이겼다는⎵의미가⎵상쇄된다.
>
> 그런⎵점에서⎵보자면⎵이재명⎵지사와⎵주류⎵간⎵투쟁은⎵간단치⎵않은⎵싸움이⎵될⎵수밖에⎵없다.
#### 품사 분석
##### KKMA
```text
그렇/VA+ㄴ데/EC 프레임/NNG 싸움/NNG+이/JKC 되/VV+면/EC 법정/NNG 싸움/NNG+에서/JKB 이기/VV+더라도/EC 궁극적/NNG+으로/JKB 지/VV+ㄴ/ETM 셈/NNB+이/JKC 되/VV+ㄹ/ETM 수/NNB 있/VV+다/EF+./SF
이기/VV+ㄴ다/EC 하/VV+어도/EC 프레임/NNG+에서/JKB 밀리/VV+어/EC 버리/VX+면/EC 이기/VV+었/EP+다는/ETM 의미/NNG+가/JKS 상쇄/NNG+되/XSV+ㄴ다/EF+./SF
그런/MM 점/NNG+에서/JKB 보/VV+자면/EC 이재명/NNG 지사/NNG+와/JKB 주류/NNG 간/NNG 투쟁/NNG+은/JX 간단/XR+하/XSA+지/EC 않/VX+은/ETM 싸움/NNG+이/JKC 되/VV+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
```

##### Twitter
```text
그런데/JC 프레임/NNG 싸움/NNG+이/JX 되면/VV 법정/NNG 싸움/NNG+에서/JX 이기더라도/VV 궁극/NNG+적/XSO+으로/JX 진/NNG 셈/NNG+이/JX 될/VV 수/NNG 있다/VA+./SF
이긴다/VV 해도/NNG 프레임/NNG+에서/JX 밀려/VV+버리면/VV 이겼다는/VV 의미/NNG+가/JX 상쇄된다/NNG+./SF
그런/VA 점/NNG+에서/JX 보자면/VV 이재명/NNG 지사/NNG+와/JX 주류/NNG 간/NNG 투쟁/NNG+은/JX 간단/NNG+치/NNG 않은/VV 싸움/NNG+이/JX 될/VV 수/NNG+밖에/JX 없다/VA+./SF
```

##### Eunjeon
```text
그런데/MAJ 프레임/NNG 싸움/NNG+이/JKS 되/VV+면/EC 법정/NNG 싸움/NNG+에서/JKB 이기/VV+더라도/EC 궁극/NNG+적/XSN+으로/JKB 지/VV+ᆫ/ETM 셈/NNB+이/JKC 되/VV+ᆯ/ETM 수/NNB 있/VV+다/EF ./SF
이기/VV+ᆫ다/EC 하/VV+아도/EC 프레임/NNG+에서/JKB 밀리/VV+어/EC 버리/VX+면/EC 이기/VV+었/EP+다는/ETM 의미/NNG+가/JKS 상쇄/NNG+되/XSV+ᆫ다/EF ./SF
그런/MM 점/NNG+에서/JKB 보/VV+자면/EC 이재명/NNP 지사/NNG+와/JC 주류/NNG 간/NNB 투쟁/NNG+은/JX 간단/XR+하/XSA+지/EC 않/VX+은/ETM 싸움/NNG+이/JKS 되/VV+ᆯ/ETM 수/NNB+밖에/JX 없/VA+다/EF ./SF
```

##### KOMORAN
```text
그런데/MAJ 프레임/NNP 싸움/NNG+이/JKS 되/VV+면/EC 법정/NNP 싸움/NNG+에서/JKB 이기/VV+더라도/EC 궁극/NNG+적/XSN+으로/JKB 지/VX+ㄴ/ETM 셈/NNB+이/JKS 되/VV+ㄹ/ETM 수/NNB 있/VX+다/EF+./SF
이기/VV+ㄴ다/EC 하/VV+아도/EC 프레임/NNP+에서/JKB 밀리/VV+어/EC+버리/VX+면/EC 이기/VV+었/EP+다는/ETM 의미/NNG+가/JKS 상쇄/NNG+되/XSV+ㄴ다/EF+./SF
그런/MM 점/NNB+에서/JKB 보/VV+자면/EC 이재명/NNP 지사/NNP+와/JC 주류/NNP 간/NNB 투쟁/NNG+은/JX 간단/XR+하/XSA+지/EC 않/VX+은/ETM 싸움/NNG+이/JKS 되/VV+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
```

##### Rhino
```text
그런데/MAJ 프레임/NNG 싸움/NNG+이/JKS 되/VV+면/EC 법정/NNG 싸움/NNG+에서/JKB 이기/NNG+더라도/EC 궁극/NNG+적/XSN+으로/JKB 지/VV+ㄴ/ETM 셈/NNB+이/JKS 되/VV+ㄹ/ETM 수/NNB 있/VX+다/EF ./SF
이기/VV+ㄴ다/EF 하/VV+어/EC+도/JX 프레임/NNG+에서/JKB 밀리/VV+어/EC+버리/VX+면/EC 이기/VV+었/EP+다는/ETM 의미/NNG+가/JKS 상쇄되/VV+ㄴ다/EF ./SF
그런/MM 점/NNG+에서/JKB 보/VV+자/EF+면/EC 이재명/NNP 지사/NNG+와/JC 주류/NNG 간/MAG 투쟁/NNG+은/JX 간단/NNG+치/XSN 않/VX+은/ETM 싸움/NNG+이/JKS 되/VV+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF ./SF
```

##### Arirang
```text
그/MAG 런데/NNG 프레임/NNG 싸우/VV+ㅁ/ETN+이/JX 되/VV+면/EF 법정/NNG 싸우/VV+ㅁ/ETN+에서/JX 이기/VV+더라도/EF 궁극/NNG 적/NNG+으로/JX 진/NNG 셈/NNG+이/JX 될 수/NNG 있/VV+다/EF ./SF
이기/VV+ㄴ다/EF 해도/NNG 프레/NNG+이/XSV+ㅁ/ETN+에서/JX 밀/VV+려/EF 버리/VV+면/EF 이기/VV+었/EP+다는/EF 의미/NNG+가/JX 상쇄/NNG+되/XSV+ㄴ다/EF ./SF
그런/MAG 점/NNG+에서/JX 보/VV+자면/EF 이재/NNG 명/NNG 지사/NNG+와/JX 주류/NNG 간/NNG 투쟁/NNG+은/JX 간단/NNG 치/NNG 않/VV+은/EF 싸우/VV+ㅁ/ETN+이/JX 될/NNG 수/NNG+밖에/JX 없/VV+다/EF ./NA
```

##### Hannanum
```text
그런데/MAJ 프레/NNG+이/VCP+ㅁ/ETN 싸우/VV+ㅁ/ETN+이/JKS 되/VV+면/EC 법정/NNG 싸우/VV+ㅁ/ETN+에서/JKB 이/NP+이/VCP+기/ETN+이/VCP+더라도/EC 궁극/NNG+적/NNG+으로/JKB 지/VX+ㄴ/ETM 셈/NNB+이/JKC 되/VV+ㄹ/ETM 수/NNB 있/VX+다/EF ./SF
이기/VV+ㄴ다/EF 하/VV+어도/EC 프레/NNG+이/VCP+ㅁ/ETN+에서/JKB 밀리/VV+어/EC+버리/VX+면/EC 이기/VV+었/EP+다/EF+는/ETM 의미/NNG+가/JKC 상쇄/NNG+되/XSV+ㄴ다/EF ./SF
그러/VV+ㄴ/ETM 점/NNM+에서/JKB 보/VX+자면/EC 이재/NNG+명/NNG 지사/NNG+와/JC 주류/NNG 가/VX+ㄴ/ETM 투쟁/NNG+은/JX 간단/NNG+치/NNG 않/VX+은/ETM 싸우/VV+ㅁ/ETN+이/JKS 되/VV+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF ./SF
```

##### Daon
```text
그런데/MAJ 프레임/NNG 싸움/NNG+이/JKS 되/VV+면/EC 법정/NNG 싸움/NNG+에서/JKB 이기/VV+더라도/EC 궁극/NNG+적/XSN+으로/JKB 지/VV+ㄴ/ETM 셈/NNB+이/JKC 되/VV+ㄹ/ETM 수/NNB 있/VA+다/EF+./SF
이기/VV+ㄴ다/EC 하/VV+어도/EC 프레임/NNG+에서/JKB 밀리/VV+어/EC+버리/VX+면/EC 이기/VV+었/EP+다는/ETM 의미/NNG+가/JKS 상쇄/NNG+되/XSV+ㄴ다/EF+./SF
그런/MM 점/NNG+에서/JKB 보/VV+자면/EC 이재명/NNP 지사/NNG+와/JC 주류/NNG 간/NNG 투쟁/NNG+은/JX 간단/NNG+하/XSA+지/EC 않/VX+은/ETM 싸움/NNG+이/JKS 되/VV+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
```

##### ETRI
```text
그런데/MAJ 프레임/NNG 싸움/NNG+이/JKC 되/VV+면/EC 법정/NNG 싸움/NNG+에서/JKB 이기/VV+더라도/EC 궁극적/NNG+으로/JKB 지/VV+ㄴ/ETM 셈/NNB+이/JKC 되/VV+ㄹ/ETM 수/NNB 있/VA+다/EF+./SF
이기/VV+ㄴ다/EF 하/VV+어도/EC 프레임/NNG+에서/JKB 밀리/VV+어/EC+버리/VX+면/EC 이기/VV+었/EP+다는/ETM 의미/NNG+가/JKS 상쇄되/VV+ㄴ다/EF+./SF
그런/MM 점/NNG+에서/JKB 보/VV+자면/EC 이재명/NNP 지사/NNG+와/JC 주류/NNG 간/NNB 투쟁/NNG+은/JX 간단하/VA+지/EC 않/VX+은/ETM 싸움/NNG+이/JKC 되/VV+ㄹ/ETM 수/NNB+밖에/JX 없/VA+다/EF+./SF
```

### 문장 번호 #7

원본 문장:
> 그러면서 "(대표님은) 제 행위에 대해 서울지방변호사회에 문의하겠다고 하셨고 조정에 관해 언급했는데, 아마 변호사법 제74조에 따른 분쟁조정신청을 하려는 것으로 생각한다"며 "궁찾사 대표님 말씀이니 아마도 궁찾사 소송인단 3천245분의 의견이 취합된 말씀일 것"이라고 말했다.
#### 어절 구분
##### KKMA
> 그러면서⎵"(⎵대표님은)⎵제⎵행위에⎵대해⎵서울지방⎵변호사회에⎵문의하겠다고
>
> 하셨고⎵조정에⎵관해⎵언급했는데,⎵아마⎵변호⎵사법⎵제⎵74⎵조에⎵따른⎵분쟁조정신청을⎵하려는⎵것으로⎵생각한다⎵"며⎵"⎵궁⎵찾사⎵대표님⎵말씀이니⎵아마도⎵궁⎵찾사⎵소송인⎵단⎵3천⎵245⎵분의⎵의견이⎵취합된⎵말씀일⎵것"⎵이라고⎵말했다.
##### Twitter
> 그러면서⎵"(대표님은)⎵제⎵행위에⎵대해⎵서울지방변호사회에⎵문의하겠다고⎵하셨고⎵조정에⎵관해⎵언급했는데,⎵아마⎵변호사법⎵제74조에⎵따른⎵분쟁조정신청을⎵하려는⎵것으로⎵생각한다"며⎵"궁찾사⎵대표님⎵말씀이니⎵아마도⎵궁찾사⎵소송인단⎵3천245분의⎵의견이⎵취합된⎵말씀일⎵것"이라고⎵말했다.
##### Eunjeon
> 그러면서⎵"⎵(⎵대표님은⎵)⎵제행위에⎵대해⎵서울⎵지방⎵변호⎵사회에⎵문의하겠다고⎵하셨고⎵조정에⎵관해⎵언급했는데⎵,⎵아마⎵변호사법⎵제⎵74⎵조에⎵따른⎵분쟁⎵조정⎵신청을⎵하려는⎵것으로⎵생각한다⎵"⎵며⎵"⎵궁찾사⎵대표님⎵말씀이⎵니⎵아마도⎵궁⎵찾사⎵소송⎵인단⎵3⎵천⎵245⎵분의⎵의견이⎵취합된⎵말씀일⎵것⎵"⎵이라고⎵말했다⎵.
##### KOMORAN
> 그러면서⎵"(대표님은)⎵제⎵행위에⎵대하여⎵서울지방변호사회에⎵문의하겠다고⎵하셨고⎵조정에⎵관하여⎵언급하였는데,⎵아마⎵변호사법⎵제74조에⎵따른⎵분쟁조정신청을⎵하려는⎵것으로⎵생각한다"이며⎵"궁찾사⎵대표님⎵말씀이니⎵아마도⎵궁찾사⎵소송인단⎵3천245분의⎵의견이⎵취합된⎵말씀일⎵것"이라고⎵말하였다.
##### Rhino
> 그러면서⎵"⎵(⎵대표님은⎵)⎵제⎵행위에⎵대해⎵서울지방변호사회에⎵문의하겠다고⎵하셨고⎵조정에⎵관해⎵언급했는데⎵,⎵아마⎵변호사법⎵제74조에⎵따른⎵분쟁조정신청을⎵하려는⎵것으로⎵생각한다⎵"⎵며⎵"⎵궁찾사⎵대표님⎵말씀이니⎵아마도⎵궁찾사⎵소송인단⎵3천245분의⎵의견이⎵취합된⎵말씀일⎵것⎵"⎵이라고⎵말했다⎵.
##### Arirang
> 그⎵러면서⎵"⎵(⎵대표⎵님은⎵)⎵제⎵행위에⎵대해⎵서울⎵지방⎵변호사⎵회에⎵문의⎵하겠다고⎵하셨고⎵조정에⎵관해⎵언급했는데⎵,⎵아마⎵변호사법⎵제74⎵조에⎵따른⎵분쟁⎵조정⎵신청을⎵하려는⎵것으로⎵생각한다⎵"⎵며⎵"⎵궁⎵찾사⎵대표⎵님⎵말씀이니⎵아마도⎵궁⎵찾사⎵소송인⎵단 3천245⎵분의⎵의견이⎵취합된⎵말씀일⎵것⎵"⎵이라고⎵말했다⎵.
##### Hannanum
> 그러면서⎵"(대표님은)⎵제⎵행위에⎵대해⎵서울지방변호사회에⎵문의하겠다고⎵하셨고⎵조정에⎵관해⎵언급했는데,⎵아마⎵변호사법⎵제74조에⎵따른⎵분쟁조정신청을⎵하려는⎵것으로⎵생각한다"며⎵"궁찾사⎵대표님⎵말씀이니⎵아마도⎵궁찾사⎵소송인단⎵3천245분의⎵의견이⎵취합된⎵말씀일⎵것"이라고⎵말했다⎵.
##### Daon
> 그러면서⎵"(대표님은)⎵제⎵행위에⎵대해⎵서울지방변호사회에⎵문의하겠다고⎵하셨고⎵조정에⎵관해⎵언급했는데,⎵아마⎵변호사법⎵제74조에⎵따른⎵분쟁조정신청을⎵하려는⎵것으로⎵생각한다"며⎵"궁찾사⎵대표님⎵말씀이니⎵아마도⎵궁찾사⎵소송인단⎵3천245분의⎵의견이⎵취합된⎵말씀일⎵것"이라고⎵말했다.
##### ETRI
> 그러면서⎵"(대표님은)⎵제⎵행위에⎵대해⎵서울지방변호사회에⎵문의하겠다고⎵하셨고⎵조정에⎵관해⎵언급했는데,⎵아마⎵변호사법⎵제74조에⎵따른⎵분쟁조정신청을⎵하려는⎵것으로⎵생각한다"며⎵"궁찾사⎵대표님⎵말씀이니⎵아마도⎵궁찾사⎵소송인단⎵3천245분의⎵의견이⎵취합된⎵말씀일⎵것"이라고⎵말했다.
#### 품사 분석
##### KKMA
```text
그러/VV+면서/EC "/SS+(/SS 대표/NNG+님/XSN+은/JX+)/SS 제/NP 행위/NNG+에/JKB 대/NNG+하/XSV+어/EC 서울/NNG+지방/NNG 변호/NNG+사회/NNG+에/JKB 문의/NNG+하/XSV+겠/EP+다고/EF
하/VV+시/EP+었/EP+고/EC 조정/NNG+에/JKB 관하/VV+어/EC 언급/NNG+하/XSV+었/EP+는데/EC+,/SP 아마/MAG 변호/NNG 사법/NNG 제/NP 74/NR 조/NNB+에/JKB 따르/VV+ㄴ/ETM 분쟁/NNG+조정/NNG+신청/NNG+을/JKO 하/VV+려는/ETM 것/NNB+으로/JKB 생각/NNG+하/XSV+ㄴ다/EC "/SS+며/JC "/SS 궁/NNG 찾사/NF 대표/NNG+님/XSN 말씀/NNG+이/VCP+니/EC 아마도/MAG 궁/NNG 찾사/NF 소송인/NNG 닿/VV+ㄴ/ETM 3/NR+천/NR 245/NR 분/NNB+의/JKG 의견/NNG+이/JKS 취합/NNG+되/XSV+ㄴ/ETM 말씀/NNG+이/VCP+ㄹ/ETM 것/NNB+"/SS 이/VCP+라고/EC 말/NNG+하/XSV+었/EP+다/EF+./SF
```

##### Twitter
```text
그러면서/VA "(/SF+대표/NNG+님/XSO+은/JX+)/SF 제/NNG 행위/NNG+에/JX 대해/NNG 서/MM+울/MM+지방/NNG+변호/NNG+사회/NNG+에/JX 문의/NNG+하겠다고/VV 하셨고/VV 조정/NNG+에/JX 관해/NNG 언급/NNG+했는데/VV+,/SF 아마/NNG 변호/NNG+사법/NNG 제/NNG+74조/NR+에/SL 따른/VV 분쟁/NNG+조정/NNG+신청/NNG+을/JX 하려는/VV 것/NNG+으로/JX 생각/NNG+한다/VV+"/SF+며/NNG "/SF+궁/NNG+찾사/VV 대표/NNG+님/XSO 말씀/NNG+이니/JX 아마도/MAG 궁/NNG+찾사/VV 소송/NNG+인/JX+단/NNG 3천/NR+245분/NR+의/SL 의견/NNG+이/JX 취합된/NNG 말씀/NNG+일/NNG 것/NNG+"/SF+이라고/JX 말/NNG+했다/VV+./SF
```

##### Eunjeon
```text
그러/VV+면서/EC "/SW (/SS 대표/NNG+님/XSN+은/JX )/SS 제/XPN+행위/NNG+에/JKB 대하/VV+아/EC 서울/NNP 지방/NNG 변호/NNG 사회/NNG+에/JKB 문의/NNG+하/XSV+겠/EP+다고/EC 않/VX+시/EP+었/EP+고/EC 조정/NNG+에/JKB 관하/VV+아/EC 언급/NNG+하/XSV+았/EP+는데/EC ,/SP 아마/MAG 변호/NNG+사/NNG+법/NNG 제/XPN 74/SN 조/NNG+에/JKB 따르/VV+ᆫ/ETM 분쟁/NNG 조정/NNG 신청/NNG+을/JKO 하/VV+려는/ETM 것/NNB+으로/JKB 생각/NNG+하/XSV+ᆫ다/EC "/SW 며/EC "/SW 궁찾사/NA 대표/NNG+님/XSN 말씀/NNG+이/VCP 니/EC 아마도/MAG 궁/NNG 찾/VV+사/EC 소송/NNG 인단/NNG 3/SN 천/NR 245/SN 분/NNM+의/JKG 의견/NNG+이/JKS 취합/NNG+되/XSV+ᆫ/ETM 말씀/NNG+이/VCP+ᆯ/ETM 것/NNB "/SW 이라고/JKQ 말/NNG+하/XSV+았/EP+다/EF ./SF
```

##### KOMORAN
```text
그러면서/MAJ "/SS+(/SS+대표/NNG+님/XSN+은/JX+)/SS 제/XPN 행위/NNG+에/JKB 대하/VV+아/EC 서울지방변호사회/NNP+에/JKB 문의/NNG+하/XSV+겠/EP+다고/EC 하/VV+시/EP+었/EP+고/EC 조정/NNG+에/JKB 관하/VV+아/EC 언급/NNG+하/XSV+았/EP+는데/EC+,/SP 아마/MAG 변호사법/NNP 제/XPN+74/SN+조에/NNP 따르/VV+ㄴ/ETM 분쟁/NNG+조정/NNP+신청/NNP+을/JKO 하/VV+려는/ETM 것/NNB+으로/JKB 생각/NNG+하/XSV+ㄴ다/EC+"/SS+이/VCP+며/EC "/SS+궁/NNG+찾/VV+사/EC 대표/NNG+님/XSN 말씀/NNG+이/VCP+니/EC 아마도/MAG 궁/NNG+찾/VV+사/EC 소송/NNG+이/VCP+ㄴ단/ETM 3/SN+천/NR+245/SN+분/NNB+의/JKG 의견/NNG+이/JKS 취합/NNG+되/XSV+ㄴ/ETM 말씀/NNG+이/VCP+ㄹ/ETM 것/NNB+"/SS+이라고/JKQ 말/NNG+하/XSV+았/EP+다/EF+./SF
```

##### Rhino
```text
그러면/MAJ+서/JKB "/SS (/SS 대표/NNG+님/XSN+은/JX )/SS 제/NP 행위/NNG+에/JKB 대하/VV+어/EC 서울지방변호사회/NNP+에/JKB 문의하/VV+겠/EP+다고/EC 하/VV+시/EP+었/EP+고/EC 조정/NNG+에/JKB 관해/NNG 언급하/VV+았/EP+는데/EC ,/SP 아마/MAG 변호사법/NNG 제/XPN+74/SN+조/NNB+에/JKB 따른/MM 분쟁/NNG+조정/NNG+신청/NNG+을/JKO 하/VV+려는/ETM 것/NNB+으로/JKB 생각하/VV+ㄴ다/EF "/SS 며/EC "/SS 궁/NNG+찾/VV+사/EC 대표/NNG+님/XSN 말씀/NNG+이/VCP+니/EC 아마도/MAG 궁/NNG+찾/VV+사/EC 소송/NNG+인단/NNG 3/SN+천/NNG+245/SN+분/XSN+의/JKG 의견/NNG+이/JKS 취합되/VV+ㄴ/ETM 말씀/NNG+이/VCP+ㄹ/ETM 것/NNB "/SS 이/VCP+라고/EC 말하/VV+았/EP+다/EF ./SF
```

##### Arirang
```text
그/MAG 러면서/NNG "/SS (/SS 대표/NNG 님/NNG+은/JX )/SS 제/NNG 행위/NNG+에/JX 대해/NNG 서울/NNG 지방/NNG 변호사/NNG 회/NNG+에/JX 문의/NNG 하/VV+겠/EP+다고/EF 하/VV+시었/EP+고/EF 조정/NNG+에/JX 관해/NNG 언급/NNG+하/XSV+었/EP+는데/EF ,/SP 아마/NNG 변호사법/NNG 제74/NNG 조/NNG+에/JX 따르/VV+ㄴ/EF 분쟁/NNG 조정/NNG 신청/NNG+을/JX 하/VV+려는/EF 것/NNG+으로/JX 생각/NNG+하/XSV+ㄴ다/EF "/SS 며/NNG "/SS 궁/NNG 찾/VV+사/EF 대표/NNG 님/NNG 말씀/NNG+이/XSV+니/EF 아마/NNG+도/JX 궁/NNG 찾/VV+사/EF 소송/NNG+이/XSV+ㄴ/EF 단 3천245/NNG 분/NNG+의/JX 의견/NNG+이/JX 취합/NNG+되/XSV+ㄴ/EF 말씀/NNG+이/XSV+ㄹ/EF 것/NNG "/SS 이/NNG+라고/JX 말/NNG+하/XSV+었/EP+다/EF ./NA
```

##### Hannanum
```text
그러/VV+면서/EC "/SS+(/SS+대표/NNG+님/NNG+은/JX+)/SS 저/NP+의/JKG 행위/NNG+에/JKB 대/VV+어/EC+하/VX+어/EC 서울지방/NNG+변호사회/NNG+에/JKB 문의/NNG+하/XSV+겠/EP+다/EF+고/JKQ 하/VX+셨/EP+고/EC 조정/NNG+에/JKB 관하/VV+어/EC 언급/NNG+하/XSV+었/EP+는데/EC+,/SP 아마/MAG 변호사법/NNG 제74조/NNG+에/JKB 따르/VV+ㄴ/ETM 분쟁조정/NNG+신청/NNG+을/JKO 하/VV+려는/ETM 것/NNB+으로/JKB 생각한다"며/NNG "궁찾사/NNG 대표/NNG+님/NNG 말씀/NNG+이/VCP+니/EC 아마/MAG+도/JX 궁찾사/NNG 소송/NNG+이/VCP+ㄴ단/ETM 3/NR+천/NR+245/NR+분/NNM+의/JKG 의견/NNG+이/JKC 취합/NNG+되/XSV+ㄴ/ETM 말씀/NNG+이/VCP+ㄹ/ETM 것/NNB+"/SS+이/VCP+라/EF+고/JKQ 말/NNG+하/XSV+었/EP+다/EF ./SF
```

##### Daon
```text
그러/VV+면서/EC "/SP+(/SP+대표/NNG+님/XSN+은/JX+)/SP 제/MM 행위/NNG+에/JKB 대하/VV+어/EC 서울지방변호사회/NNP+에/JKB 문의/NNG+하/XSV+겠/EP+다고/EC 하/VV+시/EP+었/EP+고/EC 조정/NNG+에/JKB 관하/VV+어/EC 언급/NNG+하/XSV+었/EP+는데/EC+,/SP 아마/MAG 변호사법/NNG 제/MM+74/SN+조/NNB+에/JKB 따르/VV+ㄴ/ETM 분쟁조정/NNG+신청/NNG+을/JKO 하/VV+려는/ETM 것/NNB+으로/JKB 생각/NNG+하/XSV+ㄴ다/EF+"/SP+며/EC "/SP+궁/NNG+찾/VV+사/EC 대표/NNG+님/XSN 말씀/NNG+이/VCP+니/EC 아마도/MAG 궁/NNG+찾/VV+사/EC 소송인단/NNG 3/SN+천/NR+245/SN+분/NNB+의/JKG 의견/NNG+이/JKS 취합/NNG+되/XSV+ㄴ/ETM 말씀/NNG+이/VCP+ㄹ/ETM 것/NNB+"/SP+이/VCP+라고/EC 말/NNG+하/XSV+었/EP+다/EF+./SF
```

##### ETRI
```text
그러/VV+면서/EC "/SS+(/SS+대표/NNG+님/XSN+은/JX+)/SS 제/XPN 행위/NNG+에/JKB 대하/VV+어/EC 서울/NNP+지방/NNG+변호/NNG+사회/NNG+에/JKB 문의하/VV+겠/EP+다고/EC 하/VV+시/EP+었/EP+고/EC 조정/NNG+에/JKB 관하/VV+어/EC 언급하/VV+었/EP+는데/EC+,/SP 아마/MAG 변호사법/NNG 제/XPN+74/SN+조/NNB+에/JKB 따르/VV+ㄴ/ETM 분쟁/NNG+조정/NNG+신청/NNG+을/JKO 하/VV+려는/ETM 것/NNB+으로/JKB 생각하/VV+ㄴ다/EF+"/SS+며/EC "/SS+궁찾사/NNG 대표/NNG+님/XSN 말씀/NNG+이/VCP+니/EC 아마도/MAG 궁찾사/NNG 소송/NNG+인단/NNG 3/SN+천/NR+245/SN+분/XSN+의/JKG 의견/NNG+이/JKS 취합/NNG+되/XSV+ㄴ/ETM 말씀/NNG+이/VCP+ㄹ/ETM 것/NNB+"/SS+이라고/JKQ 말하/VV+었/EP+다/EF+./SF
```

### 문장 번호 #8

원본 문장:
> 이 변호사는 "위임계약은 계약당사자 사이의 신뢰를 기반으로 하는 계약이니 신뢰 관계가 깨졌다고 생각하시는 이상 제가 궁찾사를 대리하는 것은 위임계약 본질에 어긋난다"며 "깔끔하게 물러나겠다"고 덧붙였다.
#### 어절 구분
##### KKMA
> 이⎵변호사는⎵"⎵위임계약은⎵계약⎵당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고
>
> 생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임계약⎵본질에⎵어긋난다⎵"며⎵"⎵깔끔하게⎵물러나겠다⎵"고⎵덧붙였다.
##### Twitter
> 이⎵변호사는⎵"위임계약은⎵계약당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고⎵생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임계약⎵본질에⎵어긋난다"며⎵"깔끔하게⎵물러나겠다"고⎵덧붙였다.
##### Eunjeon
> 이⎵변호사는⎵"⎵위임⎵계약은⎵계약⎵당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이⎵니⎵신뢰⎵관계가⎵깨졌다고⎵생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임⎵계약⎵본질에⎵어긋난다⎵"⎵며⎵"⎵깔끔하게⎵물러나겠다⎵"⎵고⎵덧붙였다⎵.
##### KOMORAN
> 이⎵변호사는⎵"위임계약은⎵계약당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고⎵생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임계약⎵본질에⎵어긋난다"이며⎵"깔끔하게⎵물러나겠다"고⎵덧붙였다.
##### Rhino
> 이⎵변호사는⎵"⎵위임계약은⎵계약당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고⎵생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임계약⎵본질에⎵어긋난다⎵"⎵며⎵"⎵깔끔하게⎵물러나겠다⎵"⎵고⎵덧붙였다⎵.
##### Arirang
> 이⎵변호사는⎵"⎵위임⎵계약은⎵계약⎵당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고⎵생각하⎵시는⎵이상⎵제가⎵궁⎵찾사⎵를⎵대리⎵하는⎵것은⎵위임계약⎵본질에⎵어긋난다⎵"⎵며⎵"⎵깔끔⎵하게⎵물러나겠다⎵"⎵고⎵덧붙였다⎵.
##### Hannanum
> 이⎵변호사는⎵"위임계약은⎵계약당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고⎵생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임계약⎵본질에⎵어긋난다"며⎵"깔끔하게⎵물러나겠다"고⎵덧붙였다⎵.
##### Daon
> 이⎵변호사는⎵"위임계약은⎵계약당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고⎵생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임계약⎵본질에⎵어긋난다"며⎵"깔끔하게⎵물러나겠다"고⎵덧붙였다.
##### ETRI
> 이⎵변호사는⎵"위임계약은⎵계약당사자⎵사이의⎵신뢰를⎵기반으로⎵하는⎵계약이니⎵신뢰⎵관계가⎵깨졌다고⎵생각하시는⎵이상⎵제가⎵궁찾사를⎵대리하는⎵것은⎵위임계약⎵본질에⎵어긋난다"며⎵"깔끔하게⎵물러나겠다"고⎵덧붙였다.
#### 품사 분석
##### KKMA
```text
이/MM 변호사/NNG+는/JX "/SS 위임/NNG+계약/NNG+은/JX 계약/NNG 당사자/NNG 사이/NNG+의/JKG 신뢰/NNG+를/JKO 기반/NNG+으로/JKB 하/VV+는/ETM 계약/NNG+이/VCP+니/EC 신뢰/NNG 관계/NNG+가/JKS 깨지/VV+었/EP+다고/EF
생각/NNG+하/XSV+시/EP+는/ETM 이상/NNG 제가/NNG 궁찾사/NF+를/JKO 대리/NNG+하/XSV+는/ETM 것/NNB+은/JX 위임/NNG+계약/NNG 본질/NNG+에/JKB 어긋나/VV+ㄴ다/EC "/SS+며/JC "/SS 깔끔/XR+하/XSA+게/EC 물러나/VV+겠/EP+다/EC "/SS+고/JC 덧붙이/VV+었/EP+다/EF+./SF
```

##### Twitter
```text
이/NNG 변호사/NNG+는/JX "/SF+위임/NNG+계약/NNG+은/JX 계약/NNG+당사자/NNG 사이/NNG+의/JX 신뢰/NNG+를/JX 기반/NNG+으로/JX 하는/VV 계약/NNG+이니/JX 신뢰/NNG 관계/NNG+가/JX 깨졌다고/VV 생각/NNG+하시는/VV 이상/NNG 제/NNG+가/JX 궁/NNG+찾/VV+사를/VV 대리/NNG+하는/VV 것/NNG+은/JX 위임/NNG+계약/NNG 본질/NNG+에/JX 어긋난다/VV+"/SF+며/NNG "/SF+깔끔하게/VA 물러나겠다/VV+"/SF+고/NNG 덧붙였다/VV+./SF
```

##### Eunjeon
```text
이/MM 변호/NNG+사/NNG+는/JX "/SW 위임/NNG 계약/NNG+은/JX 계약/NNG 당사/NNG+자/NNG 사이/NNG+의/JKG 신뢰/NNG+를/JKO 기반/NNG+으로/JKB 하/VV+는/ETM 계약/NNG+이/VCP 니/EC 신뢰/NNG 관계/NNG+가/JKS 깨지/VV+었/EP+다고/EC 생각/NNG+하/XSV+시/EP+는/ETM 이상/NNG 제/NP+가/JKS 궁찾사를/NA 대리/NNG+하/XSV+는/ETM 것/NNB+은/JX 위임/NNG 계약/NNG 본질/NNG+에/JKB 어긋나/VV+ᆫ다/EC "/SW 며/EC "/SW 깔끔/XR+하/XSA+게/EC 물러나/VV+겠/EP+다/EC "/SW 고/JKQ 덧붙이/VV+었/EP+다/EF ./SF
```

##### KOMORAN
```text
이/MM 변호사/NNP+는/JX "/SS+위임/NNP+계약/NNP+은/JX 계약/NNG+당사자/NNG 사이/NNG+의/JKG 신뢰/NNG+를/JKO 기반/NNG+으로/JKB 하/VV+는/ETM 계약/NNG+이/VCP+니/EC 신뢰/NNG 관계/NNG+가/JKS 깨지/VV+었/EP+다고/EC 생각/NNG+하/XSV+시/EP+는/ETM 이상/NNG 제가/NNP 궁/NNG+찾/VV+사/EC+를/JKO 대리/NNG+하/XSV+는/ETM 것/NNB+은/JX 위임/NNP+계약/NNP 본질/NNG+에/JKB 어긋나/VV+ㄴ다/EC+"/SS+이/VCP+며/EC "/SS+깔끔/XR+하/XSA+게/EC 물러나/VV+겠/EP+다/EC+"/SS+고/JKQ 덧붙이/VV+었/EP+다/EF+./SF
```

##### Rhino
```text
이/MM 변호사/NNG+는/JX "/SS 위임/NNG+계약/NNG+은/JX 계약/NNG+당사자/NNG 사이/NNG+의/JKG 신뢰/NNG+를/JKO 기반/NNG+으로/JKB 하/VV+는/ETM 계약/NNG+이/VCP+니/EC 신뢰/NNG 관계/NNG+가/JKS 깨지/VV+었/EP+다고/EC 생각하/VV+시/EP+는/ETM 이상/NNG 제/NP+가/JKS 궁/NNG+찾/VV+사/NR+를/JKO 대리/VV+하/VV+는/ETM 것/NNB+은/JX 위임/NNG+계약/NNG 본질/NNG+에/JKB 어긋나/VV+ㄴ다/EF "/SS 며/EC "/SS 깔끔/XR+하/VV+게/EC 물러나/VV+겠/EP+다/EF "/SS 고/JKQ 덧붙이/VV+었/EP+다/EF ./SF
```

##### Arirang
```text
이/NNG 변호사/NNG+는/JX "/SS 위임/NNG 계약/NNG+은/JX 계약/NNG 당사자/NNG 사이/NNG+의/JX 신뢰/NNG+를/JX 기반/NNG+으로/JX 하/VV+는/EF 계약/NNG+이/XSV+니/EF 신뢰/NNG 관계/NNG+가/JX 깨지/VV+었/EP+다고/EF 생각/NNG+하/XSV+어/EF 시/VV+는/EF 이상/NNG 제가/NNG 궁/NNG 찾/VV+사/EF 를/NNG 대리/NNG 하/VV+는/EF 것/NNG+은/JX 위임계약/NNG 본질/NNG+에/JX 어긋나/VV+ㄴ다/EF "/SS 며/NNG "/SS 깔끔/MAG 하/VV+게/EF 물러나/VV+겠/EP+다/EF "/SS 고/NNG 덧붙이/NNG+이/XSV+었/EP+다/EF ./NA
```

##### Hannanum
```text
이/MM 변호사/NNG+는/JX "/SS+위임/NNG+계약/NNG+은/JX 계약당사자/NNG 사이/NNG+의/JKG 신뢰/NNG+를/JKO 기반/NNG+으로/JKB 하/VV+는/ETM 계약/NNG+이/VCP+니/EC 신뢰/NNG 관계/NNG+가/JKS 깨/VV+어/EC+지/VX+었/EP+다/EF+고/JKQ 생각/NNG+하시/NNG+는/JX 이상/NNG 저/NP+가/JKS 궁찾사/NNG+를/JKO 대리/NNG+하/XSV+는/ETM 것/NNB+은/JX 위임/NNG+계약/NNG 본질/NNG+에/JKB 어긋난다"며/NNG "/SS+깔끔/NNG+하/XSA+게/EC 무르/VV+어/EC+나/VX+겠/EP+다/EF+"/SS+고/JKQ 덧붙이/VV+었/EP+다/EF ./SF
```

##### Daon
```text
이/MM 변호사/NNG+는/JX "/SP+위임/NNG+계약/NNG+은/JX 계약당사자/NNG 사이/NNG+의/JKG 신뢰/NNG+를/JKO 기반/NNG+으로/JKB 하/VV+는/ETM 계약/NNG+이/VCP+니/EC 신뢰/NNG 관계/NNG+가/JKS 깨지/VV+었/EP+다고/EC 생각/NNG+하/XSV+시/EP+는/ETM 이상/NNG 제/NP+가/JKS 궁/NNG+찾/VV+사/EC+를/JKO 대리/NNG+하/XSV+는/ETM 것/NNB+은/JX 위임/NNG+계약/NNG 본질/NNG+에/JKB 어긋나/VV+ㄴ다/EF+"/SP+며/EC "/SP+깔끔/NNG+하/XSA+게/EC 물러나/VV+겠/EP+다/EF+"/SP+고/EC 덧붙이/VV+었/EP+다/EF+./SF
```

##### ETRI
```text
이/MM 변호사/NNG+는/JX "/SS+위임계약/NNG+은/JX 계약/NNG+당사자/NNG 사이/NNG+의/JKG 신뢰/NNG+를/JKO 기반/NNG+으로/JKB 하/VV+는/ETM 계약/NNG+이/VCP+니/EC 신뢰/NNG 관계/NNG+가/JKS 깨지/VV+었/EP+다고/EC 생각하/VV+시/EP+는/ETM 이상/NNG 제/NP+가/JKS 궁찾사/NNG+를/JKO 대리하/VV+는/ETM 것/NNB+은/JX 위임계약/NNG 본질/NNG+에/JKB 어긋나/VV+ㄴ다/EF+"/SS+며/EC "/SS+깔끔하/VA+게/EC 물러나/VV+겠/EP+다/EF+"/SS+고/JKQ 덧붙이/VV+었/EP+다/EF+./SF
```

### 문장 번호 #9

원본 문장:
> 다만 바라는 것은 이런 투쟁이 보다 나은 미래로 나아가는 진통이었으면 하는 점이다. 그렇지 않다면 이런 투쟁은 단순히 내부의 권력 투쟁으로 비칠 것이다. 국민은 그런 싸움을 바라지 않는다.
#### 어절 구분
##### KKMA
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통이었으면⎵하는⎵점이다.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다.
##### Twitter
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통이었으면⎵하는⎵점이다.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다.
##### Eunjeon
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통이⎵었으면⎵하는⎵점이⎵다⎵.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이⎵다⎵.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다⎵.
##### KOMORAN
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통였으면⎵하는⎵점이다.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다.
##### Rhino
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통이었으면⎵하는⎵점이다⎵.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다⎵.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다⎵.
##### Arirang
> 다⎵만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아⎵가는⎵진통이었으면⎵하는⎵점이다⎵.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순⎵히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다⎵.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다⎵.
##### Hannanum
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통이었으면⎵하는⎵점이다⎵.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다⎵.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다⎵.
##### Daon
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통이었으면⎵하는⎵점이다.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다.
##### ETRI
> 다만⎵바라는⎵것은⎵이런⎵투쟁이⎵보다⎵나은⎵미래로⎵나아가는⎵진통이었으면⎵하는⎵점이다.
>
> 그렇지⎵않다면⎵이런⎵투쟁은⎵단순히⎵내부의⎵권력⎵투쟁으로⎵비칠⎵것이다.
>
> 국민은⎵그런⎵싸움을⎵바라지⎵않는다.
#### 품사 분석
##### KKMA
```text
다만/MAG 바라/VV+는/ETM 것/NNB+은/JX 이런/MM 투쟁/NNG+이/JKS 보다/MAG 낫/VA+은/ETM 미래/NNG+로/JKB 나아가/VV+는/ETM 진통/NNG+이/VCP+었/EP+으면/EC 하/VX+는/ETM 점/NNG+이/VCP+다/EF+./SF
그렇/VA+지/EC 않/VX+다면/EC 이런/MM 투쟁/NNG+은/JX 단순히/MAG 내부/NNG+의/JKG 권력/NNG 투쟁/NNG+으로/JKB 비치/VV+ㄹ/ETM 것/NNB+이/VCP+다/EF+./SF
국민/NNG+은/JX 그런/MM 싸움/NNG+을/JKO 바라/VV+지/EC 않/VX+는/EP+다/EF+./SF
```

##### Twitter
```text
다만/NNG 바라는/VV 것/NNG+은/JX 이런/VA 투쟁/NNG+이/JX 보다/VV 나은/NNG 미래/NNG+로/JX 나아가는/VV 진통/NNG+이었으면/VV 하는/VV 점/NNG+이다/JX+./SF
그렇지/VA 않다면/VV 이런/VA 투쟁/NNG+은/JX 단순히/VA 내부/NNG+의/JX 권력/NNG 투쟁/NNG+으로/JX 비칠/VV 것/NNG+이다/JX+./SF
국민/NNG+은/JX 그런/VA 싸움/NNG+을/JX 바라지/NNG 않는다/VV+./SF
```

##### Eunjeon
```text
다만/MAJ 바라/VV+는/ETM 것/NNB+은/JX 이런/MM 투쟁/NNG+이/JKS 보다/MAG 낫/VA+은/ETM 미래/NNG+로/JKB 나아가/VV+는/ETM 진통/NNG+이/VCP 었/EP+으면/EC 하/VV+는/ETM 점/NNG+이/VCP 다/EF ./SF
그렇/VA+지/EC 않/VX+다면/EC 이런/MM 투쟁/NNG+은/JX 단순히/MAG 내부/NNG+의/JKG 권력/NNG 투쟁/NNG+으로/JKB 비치/VV+ᆯ/ETM 것/NNB+이/VCP 다/EF ./SF
국민/NNG+은/JX 그런/MM 싸움/NNG+을/JKO 바라/VV+지/EC 않/VX+는다/EF ./SF
```

##### KOMORAN
```text
다만/MAJ 바라/VV+는/ETM 것/NNB+은/JX 이런/MM 투쟁/NNG+이/JKS 보다/MAG 나/NP+은/JX 미래/NNG+로/JKB 나아가/VV+는/ETM 진통/NNG+이/VCP+었/EP+으면/EC 하/VV+는/ETM 점/NNB+이/VCP+다/EF+./SF
그렇/VA+지/EC 않/VX+다면/EC 이런/MM 투쟁/NNG+은/JX 단순히/MAG 내부/NNG+의/JKG 권력/NNG 투쟁/NNG+으로/JKB 비치/VV+ㄹ/ETM 것/NNB+이/VCP+다/EF+./SF
국민/NNG+은/JX 그런/MM 싸움/NNG+을/JKO 바라지/NNP 않/VX+는다/EF+./SF
```

##### Rhino
```text
다만/MAG 바라/VV+는/JX 것/NNB+은/JX 이런/MM 투쟁/NNG+이/JKS 보/NNG+다/EF 나/NP+은/JX 미래/NNG+로/JKB 나아가/VV+는/JX 진통/NNG+이/VCP+었/EP+으면/EC 하/VV+는/ETM 점/NNG+이다/EF ./SF
그렇지/IC 않/VX+다면/EC 이런/MM 투쟁/NNG+은/JX 단순히/MAG 내부/NNG+의/JKG 권력/NNG 투쟁/NNG+으로/JKB 비치/VV+ㄹ/ETM 것/NNB+이다/EF ./SF
국민/NNG+은/JX 그런/MM 싸움/NNG+을/JKO 바라지/NNG 않/VX+는다/EF ./SF
```

##### Arirang
```text
다/NNG 만/NNG 바/VV+라는/EF 것/NNG+은/JX 이런/MAG 투쟁/NNG+이/JX 보/VV+다/EF 낫/VV+은/EF 미래/NNG+로/JX 낫/VV+아/EF 가/VV+는/EF 진통/NNG+이/XSV+었/EP+으면/EF 하/VV+는/EF 점/NNG+이/XSV+다/EF ./SF
그렇/VV+지/EF 않/VV+다면/EF 이런/MAG 투쟁/NNG+은/JX 단순/NNG 히/NNG 내부/NNG+의/JX 권력/NNG 투쟁/NNG+으로/JX 비칠/NNG 것/NNG+이/XSV+다/EF ./SF
국민/NNG+은/JX 그런/MAG 싸우/VV+ㅁ/ETN+을/JX 바라/VV+지/EF 않/VV+는다/EF ./NA
```

##### Hannanum
```text
다만/MAG 바라/VV+는/ETM 것/NNB+은/JX 이런/MM 투쟁/NNG+이/JKC 보다/MAG 나/NP+은/JX 미래/NNG+로/JKB 나/VV+아/EC+가/VX+는/ETM 지/VX+ㄴ/ETM+통/NNB+이/VCP+었/EP+으면/EC 하/VV+는/ETM 점/NNM+이/VCP+다/EF ./SF
그렇/VA+지/EC 않/VX+다면/EC 이런/MM 투쟁/NNG+은/JX 단순히/MAG 내부/NNG+의/JKG 권력/NNG 투쟁/NNG+으로/JKB 비치/VV+ㄹ/ETM 것/NNB+이/VCP+다/EF ./SF
국민/NNG+은/JX 그러/VV+ㄴ/ETM 싸우/VV+ㅁ/ETN+을/JKO 바라/VV+지/EC 않/VX+는다/EF ./SF
```

##### Daon
```text
다만/MAG 바라/VV+는/ETM 것/NNB+은/JX 이런/MM 투쟁/NNG+이/JKS 보다/MAG 낫/VA+은/ETM 미래/NNG+로/JKB 나아가/VV+는/ETM 진통/NNG+이/VCP+었/EP+으면/EC 하/VV+는/ETM 점/NNG+이/VCP+다/EF+./SF
그렇/VA+지/EC 않/VX+다면/EC 이런/MM 투쟁/NNG+은/JX 단순/NNG+히/EC 내부/NNG+의/JKG 권력/NNG 투쟁/NNG+으로/JKB 비치/VV+ㄹ/ETM 것/NNB+이/VCP+다/EF+./SF
국민/NNG+은/JX 그런/MM 싸움/NNG+을/JKO 바라/VV+지/EC 않/VX+는다/EF+./SF
```

##### ETRI
```text
다만/MAG 바라/VV+는/ETM 것/NNB+은/JX 이런/MM 투쟁/NNG+이/JKS 보다/MAG 낫/VA+은/ETM 미래/NNG+로/JKB 나아가/VV+는/ETM 진통/NNG+이/VCP+었/EP+으면/EC 하/VX+는/ETM 점/NNG+이/VCP+다/EF+./SF
그렇/VA+지/EC 않/VX+다면/EC 이런/MM 투쟁/NNG+은/JX 단순히/MAG 내부/NNG+의/JKG 권력/NNG 투쟁/NNG+으로/JKB 비치/VV+ㄹ/ETM 것/NNB+이/VCP+다/EF+./SF
국민/NNG+은/JX 그런/MM 싸움/NNG+을/JKO 바라/VV+지/EC 않/VX+는다/EF+./SF
```

