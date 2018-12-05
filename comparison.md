
실제 사용 성능을 보여드리기 위해서, 카카오 뉴스에서 임의로 뉴스를 선택하고 30개 문단을 표본추출하였습니다.

실험환경: Ubuntu 16.04, **3GB** MaxHeap, **8**-core(s), Scala **2.12**

실험일시: Thu Dec 06 00:37:10 KST 2018

## 시간 성능 개관

. | ETRI API | Kakao Khaiii | 은전한닢(Mecab-ko) | 코모란 | 꼬꼬마 | 아리랑 (루씬) | 한나눔 | Open Korean Text | Daon | 라이노
--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---
초기화 | 0.0s | 0.001s | 0.0s | 0.001s | 0.03s | 0.003s | 0.001s | 0.0s | 1.064s | 0.0s
첫 문장 (사전 불러오기 포함) | 2.321s | 0.085s | 2.656s | 2.450s | 2.007s | 0.167s | 0.269s | 1.048s | 0.010s | 0.328s
이후 문장들 | 0.107±0.001s | 0.005±0.000s | 0.003±0.000s | 0.002±0.000s | 0.049±0.000s | 0.011±0.000s | 0.007±0.000s | 0.016±0.000s | 0.001±0.000s | 0.038±0.000s
오류 발생 횟수 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0

가장 __빠르게 초기화된__ 것은 `ETRI API`이며, 가장 느리게 초기화된 패키지는 `Daon`입니다.

가장 __빠르게 첫 분석을 시작한__ 것은 `Kakao Khaiii`이며, 가장 느리게 첫 분석을 시작한 패키지는 `은전한닢(Mecab-ko)`입니다.

첫 문장을 빼면, 평균적으로 __가장 빠르게 분석한__ 것은 `Daon`이며, 가장 느리게 분석한 패키지는 `ETRI API`입니다.

## Tagging 결과

이제부터, 임의로 세 문장을 선택, 품사분석 결과를 보여드립니다. 정답이 없으므로, 바르게 분석했는지의 여부는 여러분께서 판단하셔야 합니다. 다음과 같은 기준으로 평가해보시는 것을 권장합니다.


* 띄어쓰기나 어절구분은 정확한가? (바르게 분석했다면, 읽는 데 __어색함이 없어야__ 합니다)

* 고유명사나 신조어를 어떻게 분석했는가? (바르게 분석했다면, __고유명사/NNP__ 품사가 붙고, 적절히 띄어쓰기 되어야 합니다)

* 체언(명사)이 바르게 분석되었는가? (바르게 분석했다면, __N으로 시작하는 품사__ 가 붙어야 합니다)

* 용언(동사, 형용사)가 바르게 분석되었는가? (바르게 분석했다면, __V으로 시작하는 품사__ 가 붙고, 이후에, 어미(__E로 시작하는 품사__) 가 붙어야 합니다)   * 명사로도 쓰이는 동사는 N(명사)+XSV(용언화 접미사) 형태를 띄기도 합니다.

### 문장 번호 #11

원본 문장:

> 고인은 장애인이었다. 과거 차량 사고를 당해 한쪽 다리를 쓰지 못했다. 의족을 사용할 정도로 큰 사고였지만, 송씨는 포기하지 않고 생계를 이어나갔다. 송씨처럼 인근에서 구두방을 운영하는 조모(61)씨는 “20년 전 부인과 헤어진 뒤 힘들게 살았던 사람”이라며 “살아남기 위해 여러 일을 전전하다가 구두방을 차리게 된 것”이라고 안타까워했다.

#### 품사 분석

##### ETRI API
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이/VCP+었/EP+다/EF+./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당하/VV+어/EC ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못하/VX+었/EP+다/EF+./SF
의족/NNG+을/JKO ⎵ 사용하/VV+ㄹ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ㄴ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC+,/SP ⎵ 송/NNP+씨/NNB+는/JX ⎵ 포기하/VV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 잇/VV+어/EC+나가/VX+았/EP+다/EF+./SF
송/NNP+씨/NNB+처럼/JKB ⎵ 인근/NNG+에서/JKB ⎵ 구두/NNG+방/NNG+을/JKO ⎵ 운영하/VV+는/ETM ⎵ 조모/NNG+(/SS+61/SN+)/SS+씨/NNB+는/JX ⎵ “/SS+20/SN+년/NNB ⎵ 전/NNG ⎵ 부인/NNG+과/JKB ⎵ 헤어지/VV+ㄴ/ETM ⎵ 뒤/NNG ⎵ 힘들/VA+게/EC ⎵ 살/VV+았/EP+던/ETM ⎵ 사람/NNG+”/SS+이/VCP+라며/EC ⎵ “/SS+살아남/VV+기/ETN ⎵ 위하/VV+어/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전하/VV+다가/EC ⎵ 구두/NNG+방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ㄴ/ETM ⎵ 것/NNB+”/SS+이/VCP+라고/EC ⎵ 안타까워하/VV+었/EP+다/EF+./SF
```


##### Kakao Khaiii
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이/VCP+었/EP+다/EF+./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당하/VV+여/EC ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못하/VX+였/EP+다/EF+./SF
의족/NNG+을/JKO ⎵ 사용/NNG+하/XSV+ㄹ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ㄴ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC+,/SP ⎵ 송/NNP+씨/NNB+는/JX ⎵ 포기/NNG+하/XSV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 잇/VV+어/EC+나가/VX+았/EP+다/EF+./SF
송/NNP+씨/NNB+처럼/JKB ⎵ 인근/NNG+에서/JKB ⎵ 구두방/NNG+을/JKO ⎵ 운영/NNG+하/XSV+는/ETM ⎵ 조모/NNG+(/SS+61/SN+)/SS+씨/NNB+는/JX ⎵ “/SS+20/SN+년/NNB ⎵ 전/NNG ⎵ 부인/NNG+과/JC ⎵ 헤어지/VV+ㄴ/ETM ⎵ 뒤/NNG ⎵ 힘들/VA+게/EC ⎵ 살/VV+았/EP+던/ETM ⎵ 사람/NNG+”/SS+이/VCP+라며/EC ⎵ “/SS+살아남/VV+기/ETN ⎵ 위하/VV+여/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전/NNG+하/XSV+다가/EC ⎵ 구두방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ㄴ/ETM ⎵ 것/NNB+”/SS+이라고/JKQ ⎵ 안타까워하/VV+였/EP+다/EF+./SF
```


##### 은전한닢(Mecab-ko)
```text
고인/NNG+은/JX ⎵ 장애/NNG+인/NNG+이/VCP ⎵ 었/EP+다/EF ⎵ ./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당하/VV+아/EC ⎵ 한/NNG+쪽/NNG ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못하/VX+았/EP+다/EF ⎵ ./SF
의족/NNG+을/JKO ⎵ 사용/NNG+하/XSV+ᆯ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ᆫ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC ⎵ ,/SP ⎵ 송/NNP ⎵ 씨/NNB+는/JX ⎵ 포기/NNG+하/XSV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 이/VV+어/EC ⎵ 나가/VV+았/EP+다/EF ⎵ ./SF
송/NNP ⎵ 씨/NNB+처럼/JKB ⎵ 인근/NNG+에서/JKB ⎵ 구두/NNG ⎵ 방/NNG+을/JKO ⎵ 운영/NNG+하/XSV+는/ETM ⎵ 조모/NNG ⎵ (/SS ⎵ 61/SN ⎵ )/SS ⎵ 씨/NNB+는/JX ⎵ “/SS ⎵ 20/SN ⎵ 년/NNM ⎵ 전/NNG ⎵ 부인/NNG+과/NNG ⎵ 헤어지/VV+ᆫ/ETM ⎵ 뒤/NNG ⎵ 힘들/VA+게/EC ⎵ 살/VV+았/EP+던/ETM ⎵ 사람/NNG ⎵ ”/SS ⎵ 이/VCP ⎵ 라며/EC ⎵ “/SS ⎵ 살아남/VV+기/ETN ⎵ 위하/VV+아/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전/NNG+하/XSV+다가/EC ⎵ 구두/NNG ⎵ 방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ᆫ/ETM ⎵ 것/NNB ⎵ ”/SS ⎵ 이/VCP ⎵ 라고/EC ⎵ 안타까워하/VV+았/EP+다/EF ⎵ ./SF
```


##### 코모란
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이/VCP+었/EP+다/EF+./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당하/VV+아/EC ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못하/VX+았/EP+다/EF+./SF
의/NNG+족/NNG+을/JKO ⎵ 사용/NNG+하/XSV+ㄹ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ㄴ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC+,/SP ⎵ 송/NNP+씨/NNB+는/JX ⎵ 포기/NNG+하/XSV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 잇/VV+어/EC+나가/VX+았/EP+다/EF+./SF
송/NNP+씨/NNB+처럼/JKB ⎵ 인근/NNG+에서/JKB ⎵ 구두/NNP+방/NNG+을/JKO ⎵ 운영/NNG+하/XSV+는/ETM ⎵ 조모/NNP+(/SS+61/SN+)/SS+씨/NNB+는/JX ⎵ “/SS+20년/NNP ⎵ 전/MM ⎵ 부인/NNG+과/JC ⎵ 헤어지/VV+ㄴ/ETM ⎵ 뒤/NNG ⎵ 힘들/VA+게/EC ⎵ 살/VV+았/EP+던/ETM ⎵ 사람/NNG+”/SS+이/VCP+라며/EC ⎵ “/SS+살아남기/NNP ⎵ 위하/VV+아/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전/NNG+하/XSV+다가/EC ⎵ 구두/NNP+방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ㄴ/ETM ⎵ 것/NNB+”/SS+이라고/JKQ ⎵ 안타깝/VA+어/EC+하/VX+았/EP+다/EF+./SF
```


##### 꼬꼬마
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이/VCP+었/EP+다/EF+./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당해/NNG ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못/MAG+하/XSV+었/EP+다/EF+./SF
의족/NNG+을/JKO ⎵ 사용/NNG+하/XSV+ㄹ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ㄴ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC+,/SP ⎵ 송/NF ⎵ 씨/NNB+는/JX ⎵ 포기/NNG+하/XSV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 잇/VV+어/EC ⎵ 나가/VX+었/EP+다/EF+./SF
송씨/NF+처럼/JKB ⎵ 인근/NNG+에서/JKB ⎵ 구두/NNG ⎵ 방/NNG+을/JKO ⎵ 운영/NNG+하/XSV+는/ETM ⎵ 조모/NNG ⎵ (/SS+61/NR+)/SS ⎵ 씨/NNB+는/JX ⎵ “/SS+20/NR ⎵ 년/NNB ⎵ 전/NNG ⎵ 부인/NNG+과/JKB ⎵ 헤어지/VV+ㄴ/ETM ⎵ 뒤/NNG ⎵ 힘들/VA+게/EC ⎵ 살/VV+았/EP+더/EP+ㄴ/ETM ⎵ 사람/NNG+”/SS ⎵ 이/VCP+라며/EC ⎵ “/SS ⎵ 살아남/VV+기/ETN ⎵ 위하/VV+어/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전/NNG+하/XSV+다가/EC ⎵ 구두/NNG ⎵ 방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ㄴ/ETM ⎵ 것/NNB+”/SS ⎵ 이/VCP+라고/EC ⎵ 안타까워하/VV+었/EP+다/EF+./SF
```


##### 아리랑 (루씬)
```text
고인은/NNG ⎵ 장애인/NNG+이/XSV+었/EP+다/EF ⎵ ./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JX ⎵ 당/NNG+하/XSV+어/EF ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JX ⎵ 쓰/VV+지/EF ⎵ 못하/VV+었/EP+다/EF ⎵ ./SF
의족/NNG+을/JX ⎵ 사용/NNG+하/XSV+ㄹ/EF ⎵ 정도/NNG+로/JX ⎵ 큰/NNG ⎵ 사고/NNG+이/XSV+었/EP+지만/EF ⎵ ,/SP ⎵ 송씨/NNG+는/JX ⎵ 포기/NNG+하/XSV+지/EF ⎵ 않/VV+고/EF ⎵ 생계/NNG+를/JX ⎵ 이어/NNG ⎵ 나가/VV+었/EP+다/EF ⎵ ./SF
송씨/NNG+처럼/JX ⎵ 인근/NNG+에서/JX ⎵ 구두/NNG ⎵ 방/NNG+을/JX ⎵ 운영/NNG+하/XSV+는/EF ⎵ 조모/NNG ⎵ (/SS ⎵ 61/NNG ⎵ )/SS ⎵ 씨/NNG+는/JX ⎵ “/SS ⎵ 20/NNG ⎵ 년 전/NNG ⎵ 부인/NNG+과/JX ⎵ 헤어지/VV+ㄴ/EF ⎵ 뒤/NNG ⎵ 힘들/VV+게/EF ⎵ 살/VV+았/EP+던/EF ⎵ 사람/NNG ⎵ ”/SS ⎵ 이/NNG+라/JX ⎵ 며/NNG ⎵ “/SS ⎵ 살아남/VV+기/EF ⎵ 위해/NNG ⎵ 여러/NNG ⎵ 일/NNG+을/JX ⎵ 전전/NNG+하/XSV+어다가/EF ⎵ 구두/NNG ⎵ 방/NNG+을/JX ⎵ 차리/VV+게/EF ⎵ 된/NNG ⎵ 것/NNG ⎵ ”/SS ⎵ 이/NNG+라고/JX ⎵ 안타까워/NNG+하/XSV+었/EP+다/EF ⎵ ./NA
```


##### 한나눔
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이/VCP+었/EP+다/EF ⎵ ./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당하/VV+어/EC ⎵ 하/VV+ㄴ/ETM+쪽/NNB ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못하/VX+었/EP+다/EF ⎵ ./SF
의족/NNG+을/JKO ⎵ 사용/NNG+하/XSV+ㄹ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ㄴ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC+,/SP ⎵ 송씨/NNG+는/JX ⎵ 포기/NNG+하/XSV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 잇/VV+어/EC+나/VX+아/EC+가/VX+아/EP+다/EF ⎵ ./SF
송씨처럼/NNG ⎵ 인근/NNG+에서/JKB ⎵ 구두/NNG+방/NNG+을/JKO ⎵ 운영/NNG+하/XSV+는/ETM ⎵ 조모(61)씨/NNG+는/JX ⎵ “20년/NNG ⎵ 전/NNB ⎵ 부인/NNG+과/JC ⎵ 헤어지/VV+ㄴ/ETM ⎵ 뒤/NNG ⎵ 힘들/VV+게/EC ⎵ 살/VV+아/EP+ㄴ/ETM ⎵ 사람”이라/NNG+이/VCP+며/EC ⎵ “살아남기/NNG ⎵ 위하/VV+어/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전/NNG+하/XSV+다가/EC ⎵ 구두/NNG+방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ㄴ/ETM ⎵ 것”/NNG+이/VCP+라/EF+고/JKQ ⎵ 안타깝/VA+어/EC+하/VX+었/EP+다/EF ⎵ ./SF
```


##### Open Korean Text
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이었다/VV+./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JX ⎵ 당해/VA ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JX ⎵ 쓰지/VV ⎵ 못/XPV+했다/VV+./SF
의족/NNG+을/JX ⎵ 사용/NNG+할/VV ⎵ 정도/NNG+로/JX ⎵ 큰/VV ⎵ 사고였지만/VV+,/SF ⎵ 송씨/NNG+는/JX ⎵ 포기/NNG+하지/VV ⎵ 않고/VV ⎵ 생계/NNG+를/JX ⎵ 이어/VV+나갔다/VV+./SF
송씨/NNG+처럼/JX ⎵ 인근/NNG+에서/JX ⎵ 구/MM+두/MM+방/NNG+을/JX ⎵ 운영/NNG+하는/VV ⎵ 조모/NNG+(/SF+61/NR+)/SF+씨/NNG+는/JX ⎵ “/SL+20년/NR ⎵ 전/NNG ⎵ 부인과/NNG ⎵ 헤어진/VV ⎵ 뒤/NNG ⎵ 힘들게/VA ⎵ 살았던/VV ⎵ 사람/NNG+”/SL+이/MM+라며/NNG ⎵ “/SL+살아남기/VV ⎵ 위해/NNG ⎵ 여러/NNG ⎵ 일/NNG+을/JX ⎵ 전전/NNG+하다가/VV ⎵ 구/MM+두/MM+방/NNG+을/JX ⎵ 차리게/VV ⎵ 된/VV ⎵ 것/NNG+”/SL+이라고/JX ⎵ 안타까워/VA+했다/VV+./SF
```


##### Daon
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이/VCP+었/EP+다/EF+./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당하/VV+어/EC ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못하/VX+었/EP+다/EF+./SF
의족/NNG+을/JKO ⎵ 사용/NNG+하/XSV+ㄹ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ㄴ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC+,/SP ⎵ 송/NNP+씨/NNB+는/JX ⎵ 포기/NNG+하/XSV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 잇/VV+어/EC+나가/VX+았/EP+다/EF+./SF
송/NNP+씨/NNB+처럼/JKB ⎵ 인근/NNG+에서/JKB ⎵ 구두/NNG+방/NNG+을/JKO ⎵ 운영/NNG+하/XSV+는/ETM ⎵ 조모/NNG+(/SP+61/SN+)/SW+씨/NNB+는/JX ⎵ “/SW+20/SN+년/NNB ⎵ 전/NNG ⎵ 부인/NNG+과/JKB ⎵ 헤어지/VV+ㄴ/ETM ⎵ 뒤/NNG ⎵ 힘들/VA+게/EC ⎵ 살/VV+았/EP+던/ETM ⎵ 사람/NNG+”/SW+이/VCP+라며/EC ⎵ “/SW+살아남/VV+기/ETN ⎵ 위하/VV+어/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전/NNG+하/XSV+다가/EC ⎵ 구두/NNG+방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ㄴ/ETM ⎵ 것/NNB+”/SW+이/VCP+라고/EC ⎵ 안타까워하/VV+었/EP+다/EF+./SF
```


##### 라이노
```text
고인/NNG+은/JX ⎵ 장애인/NNG+이/VCP+었/EP+다/EF ⎵ ./SF
과거/NNG ⎵ 차량/NNG ⎵ 사고/NNG+를/JKO ⎵ 당해/NNG ⎵ 한쪽/NNG ⎵ 다리/NNG+를/JKO ⎵ 쓰/VV+지/EC ⎵ 못하/VV+았/EP+다/EF ⎵ ./SF
의족/NNG+을/JKO ⎵ 사용하/VV+ㄹ/ETM ⎵ 정도/NNG+로/JKB ⎵ 크/VA+ㄴ/ETM ⎵ 사고/NNG+이/VCP+었/EP+지만/EC ⎵ ,/SP ⎵ 송/NNP+씨/NNB+는/JX ⎵ 포기하/VV+지/EC ⎵ 않/VX+고/EC ⎵ 생계/NNG+를/JKO ⎵ 잇/VV+어/EC+나가/VX+았/EP+다/EF ⎵ ./SF
송/NNP+씨/NNB+처럼/JKB ⎵ 인근/NNG+에서/JKB ⎵ 구두방/NNG+을/JKO ⎵ 운영하/VV+는/ETM ⎵ 조/NNP+모/NP ⎵ (/SS ⎵ 61/SN ⎵ )/SS ⎵ 씨/NNB+는/JX ⎵ “/SS ⎵ 20/SN+년/NNB ⎵ 저/NP+는/JX ⎵ 부인/NNG+과/JC ⎵ 헤어지/VV+ㄴ/ETM ⎵ 뒤/NNG ⎵ 힘들/VA+게/EC ⎵ 살/VV+았/EP+던/ETM ⎵ 사람/NNG ⎵ ”/SS ⎵ 이/VCP+라며/EC ⎵ “/SS ⎵ 살아남/VV+기/ETN ⎵ 위하/VV+아/EC ⎵ 여러/MM ⎵ 일/NNG+을/JKO ⎵ 전전하/VV+다가/EC ⎵ 구두방/NNG+을/JKO ⎵ 차리/VV+게/EC ⎵ 되/VV+ㄴ/ETM ⎵ 것/NNB ⎵ ”/SS ⎵ 이/VCP+라고/EC ⎵ 안타깝/VA+어/EC+하/VX+았/EP+다/EF ⎵ ./SF
```


### 문장 번호 #5

원본 문장:

> 또 “현대 과학과 인간은 다른 행성에서 오는 외계생명체의 신호를 쫓는 것에만 너무 치중하는 경향이 있다”면서 “외계인이나 UFO 등을 대하는 태도에 문제가 있으며 특히 외계인에 대한 편협한 시각을 가지고 있다”고 지적했다.

#### 품사 분석

##### ETRI API
```text
또/MAG ⎵ “/SS+현대/NNG ⎵ 과학/NNG+과/JC ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VV+는/ETM ⎵ 외계/NNG+생명체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중하/VV+는/ETM ⎵ 경향/NNG+이/JKS ⎵ 있/VA+다/EF+”/SS+면서/EC ⎵ “/SS+외계인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNB+을/JKO ⎵ 대하/VV+는/ETM ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VA+으며/EC ⎵ 특히/MAG ⎵ 외계인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 편협하/VA+ㄴ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가지/VV+고/EC ⎵ 있/VX+다/EF+”/SS+고/JKQ ⎵ 지적하/VV+었/EP+다/EF+./SF
```


##### Kakao Khaiii
```text
또/MAG ⎵ “/SS+현/NNP+대/NNG ⎵ 과학/NNG+과/JC ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VV+는/ETM ⎵ 외계/NNG+생명체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중/NNG+하/XSV+는/ETM ⎵ 경향/NNG+이/JKS ⎵ 있/VV+다/EF+”/SS+면서/EC ⎵ “/SS+외계인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNB+을/JKO ⎵ 대하/VV+는/ETM ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VV+으며/EC ⎵ 특히/MAG ⎵ 외계인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 편협/NNG+하/XSA+ㄴ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가지/VV+고/EC ⎵ 있/VX+다/EF+”/SS+고/JKQ ⎵ 지적/NNG+하/XSV+였/EP+다/EF+./SF
```


##### 은전한닢(Mecab-ko)
```text
또/MAG ⎵ “/SS ⎵ 현대/NNG ⎵ 과학/NNG+과/JC ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VV+는/ETM ⎵ 외계/NNG ⎵ 생명/NNG+체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중/NNG+하/XSV+는/ETM ⎵ 경향/NNG+이/JKS ⎵ 있/VV+다/EC ⎵ ”/SS ⎵ 면서/EC ⎵ “/SS ⎵ 외계/NNG+인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNB+을/JKO ⎵ 대하/VV+는/ETM ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VA+으며/EC ⎵ 특히/MAG ⎵ 외계/NNG+인/NNG+에/JKB ⎵ 대하/VV+ᆫ/ETM ⎵ 편협/NNG+하/XSA+ᆫ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가지/VV+고/EC ⎵ 있/VX+다/EC ⎵ ”/SS ⎵ 고/EC ⎵ 지적/NNG+하/XSV+았/EP+다/EF ⎵ ./SF
```


##### 코모란
```text
또/MAJ ⎵ “/SS+현대 과학/NNP+과/JC ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VV+는/ETM ⎵ 외계/NNG+생명체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중/NNG+하/XSV+는/ETM ⎵ 경향/NNG+이/JKS ⎵ 있다/NNP+”/SS+이/VCP+면서/EC ⎵ “/SS+외계인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNB+을/JKO ⎵ 대하/VV+는/ETM ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VV+으며/EC ⎵ 특히/MAG ⎵ 외계인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 편협/NNG+하/XSV+ㄴ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가지/VV+고/EC ⎵ 있다/NNP+”/SS+고/JKQ ⎵ 지적/NNG+하/XSV+았/EP+다/EF+./SF
```


##### 꼬꼬마
```text
또/MAG ⎵ “/SS ⎵ 현대/NNG ⎵ 과학/NNG+과/JC ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VV+는/ETM ⎵ 외계/NNG ⎵ 생명체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중/NNG+하/XSV+는/ETM ⎵ 경향/NNG+이/JKS ⎵ 있/VV+다/EC+”/SS ⎵ 이/VCP+면/EC ⎵ 서/VV+어/EC ⎵ “/SS ⎵ 외계인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNB+을/JKO ⎵ 대/NNG+하/XSV+는/ETM ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VV+으며/EC ⎵ 특히/MAG ⎵ 외계/NNG+인/XSN+에/JKB ⎵ 대/NNG+하/XSV+ㄴ/ETM ⎵ 편협/NNG+하/XSV+ㄴ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가지/VV+고/EC ⎵ 있/VX+다/EC ⎵ ”/SS+고/JC ⎵ 지적/NNG+하/XSV+었/EP+다/EF+./SF
```


##### 아리랑 (루씬)
```text
또/NNG ⎵ “/SS ⎵ 현대/NNG ⎵ 과학/NNG+과/JX ⎵ 인간/NNG+은/JX ⎵ 다른/NNG ⎵ 행성/NNG+에서/JX ⎵ 오/VV+는/EF ⎵ 외계생명/NNG ⎵ 체/NNG+의/JX ⎵ 신호/NNG+를/JX ⎵ 쫓/VV+는/EF ⎵ 것/NNG+에만/JX ⎵ 너무/MAG ⎵ 치중/NNG ⎵ 하/VV+는/EF ⎵ 경향/NNG+이/JX ⎵ 있/VV+다/EF ⎵ ”/SS ⎵ 면서/NNG ⎵ “/SS ⎵ 외계/NNG ⎵ 인/NNG+이/XSV+나/EF ⎵ UFO/NNG ⎵ 등/NNG+을/JX ⎵ 대/VV+어/EC+하/VX+는/EF ⎵ 태도/NNG+에/JX ⎵ 문제/NNG+가/JX ⎵ 있/VV+으며/EF ⎵ 특히/MAG ⎵ 외계/NNG ⎵ 인/NNG+에/JX ⎵ 대한/NNG ⎵ 편협/NNG+하/XSV+ㄴ/EF ⎵ 시각/NNG+을/JX ⎵ 가지/VV+고/EF ⎵ 있/VV+다/EF ⎵ ”/SS ⎵ 고/NNG ⎵ 지적/NNG+하/XSV+었/EP+다/EF ⎵ ./NA
```


##### 한나눔
```text
또/MAJ ⎵ “현대/NNG ⎵ 과학/NNG+과/JC ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VX+는/ETM ⎵ 외계생명체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중/NNG+하/XSV+는/ETM ⎵ 경향/NNG+이/JKC ⎵ 있다”면서/NNG ⎵ “외계인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNM+을/JKO ⎵ 대/VV+어/EC+하/VX+는/ETM ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VX+으며/EC ⎵ 특히/MAG ⎵ 외계인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 편협/NNG+하/XSA+ㄴ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가/VV+아/EC+지/VX+고/EC ⎵ 있다”고/NNG ⎵ 지적/NNG+하/XSV+었/EP+다/EF ⎵ ./SF
```


##### Open Korean Text
```text
또/NNG ⎵ “/SL+현대/NNG ⎵ 과학/NNG+과/JX ⎵ 인간/NNG+은/JX ⎵ 다른/NNG ⎵ 행성/NNG+에서/JX ⎵ 오는/VV ⎵ 외계생명체/NNG+의/JX ⎵ 신호/NNG+를/JX ⎵ 쫓는/VV ⎵ 것/NNG+에만/JX ⎵ 너무/MAG ⎵ 치중/NNG+하는/VV ⎵ 경향/NNG+이/JX ⎵ 있다/VA+”/SL+면서/NNG ⎵ “/SL+외계인/NNG+이나/JX ⎵ UFO/SL ⎵ 등/NNG+을/JX ⎵ 대하/NNG+는/JX ⎵ 태도/NNG+에/JX ⎵ 문제/NNG+가/JX ⎵ 있으며/VA ⎵ 특히/MAG ⎵ 외계인/NNG+에/JX ⎵ 대한/NNG ⎵ 편협/NNG+한/JX ⎵ 시각/NNG+을/JX ⎵ 가지/NNG+고/JX ⎵ 있다/VA+”/SL+고/NNG ⎵ 지적/NNG+했다/VV+./SF
```


##### Daon
```text
또/MAG ⎵ “/SW+현대/NNG ⎵ 과학/NNG+과/JC ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VV+는/ETM ⎵ 외계/NNG+생명체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중/NNG+하/XSV+는/ETM ⎵ 경향/NNG+이/JKS ⎵ 있/VA+다/EF+”/SW+면서/EC ⎵ “/SW+외계인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNB+을/JKO ⎵ 대하/VV+는/ETM ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VA+으며/EC ⎵ 특히/MAG ⎵ 외계인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 편협/NNG+하/XSA+ㄴ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가지/VV+고/EC ⎵ 있/VX+다/EF+”/SW+고/EC ⎵ 지적/NNG+하/XSV+었/EP+다/EF+./SF
```


##### 라이노
```text
또/MAG ⎵ “/SS ⎵ 현대/NNG ⎵ 과학/NNG+과/NNG ⎵ 인간/NNG+은/JX ⎵ 다른/MM ⎵ 행성/NNG+에서/JKB ⎵ 오/VV+는/ETM ⎵ 외계/NNG+생명체/NNG+의/JKG ⎵ 신호/NNG+를/JKO ⎵ 쫓/VV+는/ETM ⎵ 것/NNB+에/JKB+만/JX ⎵ 너무/MAG ⎵ 치중하/VV+는/ETM ⎵ 경향/NNG+이/JKS ⎵ 있/VX+다/EF ⎵ ”/SS ⎵ 면/NNG+서/JKB ⎵ “/SS ⎵ 외계인/NNG+이나/JC ⎵ UFO/SL ⎵ 등/NNG+을/JKO ⎵ 대하/VV+는/JX ⎵ 태도/NNG+에/JKB ⎵ 문제/NNG+가/JKS ⎵ 있/VX+으며/EC ⎵ 특히/MAG ⎵ 외계인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 편협하/VV+ㄴ/ETM ⎵ 시각/NNG+을/JKO ⎵ 가지/VV+고/EC ⎵ 있/VX+다/EF ⎵ ”/SS ⎵ 고/JKQ ⎵ 지적하/VV+았/EP+다/EF ⎵ ./SF
```


### 문장 번호 #23

원본 문장:

> 주민들은 지난해 11월에도 리조트 입구와 서울 부영그룹 본사 앞 등지에서 17일간 집회를 열고 비슷한 목소리를 냈다. 당시 ㈜무주덕유산리조트 김시권 대표와 구천동 관광특구연합회 조병리 회장이 ‘지역과 기업이 상생의 기본정신을 바탕으로 준수할 것을 합의한다’는 협정서에 사인을 했다.

#### 품사 분석

##### ETRI API
```text
주민/NNG+들/XSN+은/JX ⎵ 지난해/NNG ⎵ 11/SN+월/NNB+에/JKB+도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JC ⎵ 서울/NNP ⎵ 부영그룹/NNP ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNB+에서/JKB ⎵ 17/SN+일/NNB+간/XSN ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷하/VA+ㄴ/ETM ⎵ 목소리/NNP+를/JKO ⎵ 내/VV+었/EP+다/EF+./SF
당시/NNG ⎵ ㈜/SW+무주덕유산리조트/NNP ⎵ 김시권/NNP ⎵ 대표/NNG+와/JC ⎵ 구천동/NNP ⎵ 관광특구/NNG+연합회/NNG ⎵ 조병리/NNP ⎵ 회장/NNG+이/JKS ⎵ ‘/SS+지역/NNG+과/JC ⎵ 기업/NNG+이/JKS ⎵ 상생/NNG+의/JKG ⎵ 기본/NNG+정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수하/VV+ㄹ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의하/VV+ㄴ다/EF+’/SS+는/JX ⎵ 협정/NNG+서/XSN+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/VV+었/EP+다/EF+./SF
```


##### Kakao Khaiii
```text
주민/NNG+들/XSN+은/JX ⎵ 지난해/NNG ⎵ 11/SN+월/NNB+에/JKB+도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JC ⎵ 서울/NNP ⎵ 부영그/NNP+룹/NNG ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNB+에서/JKB ⎵ 17/SN+일/NNB+간/XSN ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷/XR+하/XSA+ㄴ/ETM ⎵ 목소리/NNG+를/JKO ⎵ 내/VV+었/EP+다/EF+./SF
당시/NNG ⎵ ㈜/SW+무주덕유산리조/NNP+트/NNG ⎵ 김시권/NNP ⎵ 대표/NNG+와/JC ⎵ 구천동/NNP ⎵ 관광/NNG+특구/NNG+연합/NNG+회/NNP ⎵ 조병리/NNP ⎵ 회장/NNG+이/JKS ⎵ ‘/SS+지역/NNG+과/JC ⎵ 기업/NNG+이/JKS ⎵ 상생/NNG+의/JKG ⎵ 기본/NNG+정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수/NNG+하/XSV+ㄹ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의/NNG+하/XSV+ㄴ다/EC+’/SS+는/ETM ⎵ 협정서/NNG+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/VV+였/EP+다/EF+./SF
```


##### 은전한닢(Mecab-ko)
```text
주민/NNG+들/XSN+은/JX ⎵ 지난/NNG+해/NNG ⎵ 11/SN ⎵ 월/NNM+에/JKB+도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JC ⎵ 서울/NNP ⎵ 부영/NNG ⎵ 그룹/NNG ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNB+에서/JKB ⎵ 17/SN ⎵ 일/NNM ⎵ 간/NNG ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷/XR+하/XSA+ᆫ/ETM ⎵ 목/NNG+소리/NNG+를/JKO ⎵ 내/VV+았/EP+다/EF ⎵ ./SF
당시/NNG ⎵ ㈜/SW ⎵ 무주덕유산리조트/NA ⎵ 김시권/NNP ⎵ 대표/NNG+와/JC ⎵ 구천/NNP+동/NNG ⎵ 관광/NNG+특구/NNG ⎵ 연합/NNG+회/NNG ⎵ 조/NNG ⎵ 병리/NNG ⎵ 회장/NNG+이/JKS ⎵ ‘/SW ⎵ 지역/NNG+과/JC ⎵ 기업/NNG+이/JKS ⎵ 상생/NNG+의/JKG ⎵ 기본/NNG ⎵ 정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수/NNG+하/XSV+ᆯ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의/NNG+하/XSV+ᆫ다/EC ⎵ ’/SW ⎵ 는/JX ⎵ 협정/NNG ⎵ 서/NNG+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/VV+았/EP+다/EF ⎵ ./SF
```


##### 코모란
```text
주민/NNG+들/XSN+은/JX ⎵ 지난해/NNG ⎵ 11월/NNP+에/JKB+도/JX ⎵ 리조트/NNP ⎵ 입구/NNG+와/JC ⎵ 서울/NNP ⎵ 부영/NNP+그룹/NNP ⎵ 본사/NNP ⎵ 앞/NNG ⎵ 등지/NNP+에서/JKB ⎵ 17/SN+일/NR+간/NNB ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷/XR+하/XSA+ㄴ/ETM ⎵ 목소리/NNG+를/JKO ⎵ 내/VX+었/EP+다/EF+./SF
당시/NNG ⎵ ㈜/SW+무주덕유산리조트/NNP ⎵ 김시권/NNP ⎵ 대표/NNG+와/JC ⎵ 구천/NNP+동/MM ⎵ 관광/NNG+특구/NNG+연합회/NNG ⎵ 조/NNP+병리/NNG ⎵ 회장/NNG+이/JKS ⎵ ‘/SS+지역/NNG+과/JC ⎵ 기업/NNG+이/JKS ⎵ 상생/NNG+의/JKG ⎵ 기본/NNG+정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수/NNG+하/XSV+ㄹ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의/NNG+하/XSV+ㄴ다/EC+’/SS+는/JX ⎵ 협정/NNG+서/NNP+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/VV+았/EP+다/EF+./SF
```


##### 꼬꼬마
```text
주민/NNG+들/XSN+은/JX ⎵ 지난해/NNG ⎵ 11/NR+월/NNM+에/JKB+도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JKB ⎵ 서울/NNG ⎵ 부영/NNG+그룹/NNG ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNB+에서/JKB ⎵ 17/NR+일/NNM ⎵ 간/NNG ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷/XR+하/XSA+ㄴ/ETM ⎵ 목소리/NNG+를/JKO ⎵ 내/VV+었/EP+다/EF+./SF
당시/NF ⎵ ㈜/SW ⎵ 무주/NNG ⎵ 덕유산/NNP ⎵ 리조트/NNG ⎵ 기/VV+ㅁ/ETN ⎵ 시권/NNG ⎵ 대표/NNG+와/JKB ⎵ 구천동/NNP ⎵ 관광/NNG ⎵ 특/NF+구/XSN ⎵ 연합회/NNG ⎵ 조병/NNG+리/XSN ⎵ 회장/NNG+이/JKS ⎵ ‘/SS ⎵ 지역/NNG+과/JC ⎵ 기업/NNG+이/JKS ⎵ 상생/NNG+의/JKG ⎵ 기본/NNG+정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수/NNG+하/XSV+ㄹ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의/NNG+하/XSV+ㄴ다/EC+’/SS ⎵ 늘/VA+ㄴ/ETM ⎵ 협정/NNG ⎵ 서/NNG+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/VV+었/EP+다/EF+./SF
```


##### 아리랑 (루씬)
```text
주/NNG ⎵ 민/NNG ⎵ 들/VV+은/EF ⎵ 지난해/NNG ⎵ 1/NNG ⎵ 1/NNG ⎵ 월/NNG+에도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JX ⎵ 서울/NNG ⎵ 부영/NNG ⎵ 그룹/NNG ⎵ 본사/NNG ⎵ 앞 등/NNG ⎵ 지/NNG+에서/JX ⎵ 17일간/NNG ⎵ 집회/NNG+를/JX ⎵ 열/VV+고/EF ⎵ 비슷한/NNG ⎵ 목소리/NNG+를/JX ⎵ 내/VV+었/EP+다/EF ⎵ ./SF
당시/NNG ⎵ ㈜무주/NNG ⎵ 덕/NNG ⎵ 유산리조트/NNG ⎵ 김시권/NNG ⎵ 대표/NNG+와/JX ⎵ 구/NNG ⎵ 천동/NNG ⎵ 관광/NNG ⎵ 특구연합회/NNG ⎵ 조/NNG ⎵ 병리/NNG ⎵ 회장/NNG+이/JX ⎵ ‘/SS ⎵ 지역/NNG+과/JX ⎵ 기업/NNG+이/JX ⎵ 상생/NNG+의/JX ⎵ 기본/NNG ⎵ 정신/NNG+을/JX ⎵ 바탕/NNG+으로/JX ⎵ 준수/NNG+하/XSV+ㄹ/EF ⎵ 것/NNG+을/JX ⎵ 합의/NNG+하/XSV+ㄴ다/EF ⎵ ’/SS ⎵ 는/NNG ⎵ 협정/NNG ⎵ 서/NNG+에/JX ⎵ 사인/NNG+을/JX ⎵ 하/VV+었/EP+다/EF ⎵ ./NA
```


##### 한나눔
```text
주민/NNG+들/NNG+은/JX ⎵ 지난해/NNG ⎵ 11/NR+월/NNM+에/JKB+도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JC ⎵ 서울/NNG ⎵ 부영/NNG+그룹/NNG ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNB+에서/JKB ⎵ 17/NR+이/VCP+ㄹ/ETM+간/NNB ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷/NNG+하/XSA+ㄴ/ETM ⎵ 목소리/NNG+를/JKO ⎵ 내/VX+었/EP+다/EF ⎵ ./SF
당시/NNG ⎵ ㈜무주덕유산리조트/NNG ⎵ 김시권/NNG ⎵ 대표/NNG+와/JC ⎵ 구/NR+천/NR+동/NNM ⎵ 관광/NNG+특구/NNG+연합회/NNG ⎵ 조병/NNG+리/NNG ⎵ 회장/NNG+이/JKC ⎵ ‘지역/NNG+과/JC ⎵ 기업/NNG+이/JKC ⎵ 상생/NNG+의/JKG ⎵ 기본정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수/NNG+하/XSV+ㄹ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의한다’/NNG+는/JX ⎵ 협정/NNG+서/NNG+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/VX+었/EP+다/EF ⎵ ./SF
```


##### Open Korean Text
```text
주민/NNG+들/XSO+은/JX ⎵ 지난해/NNG ⎵ 11월/NR+에도/SL ⎵ 리조트/NNG ⎵ 입구/NNG+와/JX ⎵ 서울/NNG ⎵ 부영/NNG+그룹/NNG ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNG+에서/JX ⎵ 17일/NR+간/SL ⎵ 집회/NNG+를/JX ⎵ 열고/VV ⎵ 비슷한/VA ⎵ 목소리/NNG+를/JX ⎵ 냈다/VV+./SF
당시/NNG ⎵ ㈜/SL+무주덕유산리조트/NNG ⎵ 김시권/NNG ⎵ 대표/NNG+와/JX ⎵ 구천동/NNG ⎵ 관광/NNG+특/NNG+구/MM+연합/NNG+회/NNG ⎵ 조병/NNG+리/NNG ⎵ 회장/NNG+이/JX ⎵ ‘/SL+지역/NNG+과/JX ⎵ 기업/NNG+이/JX ⎵ 상생/NNG+의/JX ⎵ 기/MM+본정신/NNG+을/JX ⎵ 바탕/NNG+으로/JX ⎵ 준수/NNG+할/VV ⎵ 것/NNG+을/JX ⎵ 합의/NNG+한다/VV+’/SF+는/VV ⎵ 협정/NNG+서에/VV ⎵ 사인/NNG+을/JX ⎵ 했다/VV+./SF
```


##### Daon
```text
주민/NNG+들/XSN+은/JX ⎵ 지난해/NNG ⎵ 11/SN+월/NNB+에/JKB+도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JC ⎵ 서울/NNP ⎵ 부영/NNP+그룹/NNG ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNB+에서/JKB ⎵ 17/SN+일/NNB+간/NNG ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷하/VA+ㄴ/ETM ⎵ 목소리/NNG+를/JKO ⎵ 내/VV+었/EP+다/EF+./SF
당시/NNG ⎵ ㈜/SP+무주/NNP+덕유산/NNP+리조트/NNG ⎵ 김시권/NNG ⎵ 대표/NNG+와/JC ⎵ 구천/NNG+동/NNB ⎵ 관광특구/NNG+연합회/NNG ⎵ 조/NNB+병리/NNG ⎵ 회장/NNG+이/JKS ⎵ ‘/SW+지역/NNG+과/JC ⎵ 기업/NNG+이/JKS ⎵ 상생/NNG+의/JKG ⎵ 기본정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수/NNG+하/XSV+ㄹ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의/NNG+하/XSV+ㄴ다/EF+’/SW+는/JX ⎵ 협정서/NNG+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/VV+었/EP+다/EF+./SF
```


##### 라이노
```text
주민/NNG+들/XSN+은/JX ⎵ 지난해/NNG ⎵ 11/SN+월/NNB+에/JKB+도/JX ⎵ 리조트/NNG ⎵ 입구/NNG+와/JC ⎵ 서울/NNP ⎵ 부영/NNP+그룹/NNG ⎵ 본사/NNG ⎵ 앞/NNG ⎵ 등지/NNB+에서/JKB ⎵ 17/SN+일/NNB+간/NNB ⎵ 집회/NNG+를/JKO ⎵ 열/VV+고/EC ⎵ 비슷/XR+하/VV+ㄴ/ETM ⎵ 목소리/NNG+를/JKO ⎵ 내/VV+었/EP+다/EF ⎵ ./SF
당시/NNG ⎵ 김시권/NNP ⎵ 대표/NNG+와/JC ⎵ 구천동/NNP ⎵ 관광/NNG+특구/NNG+연합회/NNG ⎵ 조/NR+병리/NNG ⎵ 회장/NNG+이/JKS ⎵ ‘/SS ⎵ 지역/NNG+과/JC ⎵ 기업/NNG+이/JKS ⎵ 상생/NNG+의/JKG ⎵ 기본/NNG+정신/NNG+을/JKO ⎵ 바탕/NNG+으로/JKB ⎵ 준수하/VV+ㄹ/ETM ⎵ 것/NNB+을/JKO ⎵ 합의하/VV+ㄴ다/EF ⎵ ’/SS ⎵ 는/JX ⎵ 협정서/NNG+에/JKB ⎵ 사인/NNG+을/JKO ⎵ 하/XSV+았/EP+다/EF ⎵ ./SF
```

