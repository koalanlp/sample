
실제 사용 성능을 보여드리기 위해서, 카카오 뉴스에서 임의로 뉴스를 선택하고 119개 문단을 표본추출하였습니다.

실험환경: Ubuntu 18.04, **15GB** MaxHeap, **8**-core(s), Scala **2.12.9**

실험일시: Fri Dec 13 15:36:39 KST 2019

## 시간 성능 개관

. | ETRI API | Kakao Khaiii | 은전한닢(Mecab-ko) | 코모란 | 꼬꼬마 | 아리랑 (루씬) | 한나눔 | Open Korean Text | Daon | 라이노 | UTagger
--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---
초기화 | 0.0s | 0.0s | 0.0s | 0.0s | 0.001s | 0.005s | 0.001s | 0.0s | 0.884s | 0.0s | 0.0s
첫 문장 (사전 불러오기 포함) | 0.917s | 0.092s | 1.886s | 1.054s | 2.869s | 0.336s | 0.671s | 2.267s | 0.012s | 0.680s | 1.507s
이후 문장들 | 0.381±2.139s | 0.004±0.000s | 0.004±0.000s | 0.002±0.000s | 0.053±0.001s | 0.013±0.000s | 0.009±0.000s | 0.014±0.000s | 0.001±0.000s | 0.042±0.000s | 0.442±0.044s
오류 발생 횟수 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0

가장 __빠르게 초기화된__ 것은 `ETRI API`이며, 가장 느리게 초기화된 패키지는 `Daon`입니다.

가장 __빠르게 첫 분석을 시작한__ 것은 `Kakao Khaiii`이며, 가장 느리게 첫 분석을 시작한 패키지는 `꼬꼬마`입니다.

첫 문장을 빼면, 평균적으로 __가장 빠르게 분석한__ 것은 `Daon`이며, 가장 느리게 분석한 패키지는 `UTagger`입니다.

## Tagging 결과

이제부터, 임의로 세 문장을 선택, 품사분석 결과를 보여드립니다. 정답이 없으므로, 바르게 분석했는지의 여부는 여러분께서 판단하셔야 합니다. 다음과 같은 기준으로 평가해보시는 것을 권장합니다.


* 띄어쓰기나 어절구분은 정확한가? (바르게 분석했다면, 읽는 데 __어색함이 없어야__ 합니다)

* 고유명사나 신조어를 어떻게 분석했는가? (바르게 분석했다면, __고유명사/NNP__ 품사가 붙고, 적절히 띄어쓰기 되어야 합니다)

* 체언(명사)이 바르게 분석되었는가? (바르게 분석했다면, __N으로 시작하는 품사__ 가 붙어야 합니다)

* 용언(동사, 형용사)가 바르게 분석되었는가? (바르게 분석했다면, __V으로 시작하는 품사__ 가 붙고, 이후에, 어미(__E로 시작하는 품사__) 가 붙어야 합니다)   * 명사로도 쓰이는 동사는 N(명사)+XSV(용언화 접미사) 형태를 띄기도 합니다.

### 문장 번호 #18

원본 문장:

> ◇ 노영희: 거기까지 일단 하고요. 제가 두 번째 질문 하겠습니다. 그런데 사실 4+1이 불법성이 있냐, 없냐 이 논의는 차치하고. 지금 중요한 것은 4+1 내에서도 사실 이야기가 다르다, 이게 합의가 안 됐다. 이 이야기가 나오고 있습니다. 비례대표 50석, 그리고 지역구 의원을 250석으로 하는 식으로 조정하는 큰 틀에서의 합의는 됐는데, 문제는 '캡'(cap)을 씌우는 방식이라는 거죠. 비례대표 의석에 대해서. 그런데 캡을 씌우는 방식에 대해서 민주당하고 정의당 이런 당의 입장이 완전히 다른 것 같아요. 그래서 혹시 제가 지금 이건 홍 의원님께 여쭤보고 싶은 건데. 로텐더홀에 계시는 의원님들께서 밖에서 계속 그렇게 계시면 한국당의 입장을 가서 이야기할 수 있는 기회가 아예 사라질 수도 있는 거니까, 캡 씌우는 문제 관련된 부분도 중요한 것 아니겠습니까. 좀 응할 생각은 없으실까요?

#### 품사 분석

##### ETRI API
```text
◇/SW ⎵ 노영희/NNP+:/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VV+고/EC+요/JX+./SF
제/NP+가/JKS ⎵ 두/MM ⎵ 번째/NNB ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF+./SF
그런데/MAJ ⎵ 사실/MAG ⎵ 4/SN++/SW+1/SN+이/JKS ⎵ 불법성/NNG+이/JKS ⎵ 있/VA+냐/EC+,/SP ⎵ 없/VA+냐/EC ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치하/VV+고/EF+./SF
지금/MAG ⎵ 중요하/VA+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/SN++/SW+1/SN ⎵ 내/NNB+에서/JKB+도/JX ⎵ 사실/MAG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EC+,/SP ⎵ 이것/NP+이/JKS ⎵ 합의/NNG+가/JKS ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF+./SF
이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF+./SF
비례대표/NNG ⎵ 50/SN+석/NNB+,/SP ⎵ 그리고/MAJ ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/SN+석/NNB+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정하/VV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC+,/SP ⎵ 문제/NNG+는/JX ⎵ '/SS+캡/NNG+'/SS+(/SS+cap/SL+)/SS+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라는/ETM ⎵ 것/NNB+이/VCP+죠/EF+./SF
비례대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+어서/EC+./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+어서/EC ⎵ 민주당/NNP+하/XSV+고/EC ⎵ 정의당/NNP ⎵ 이런/MM ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+이/JKS ⎵ 완전히/MAG ⎵ 다른/MM ⎵ 것/NNB ⎵ 같/VA+아요/EF+./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제/NP+가/JKS ⎵ 지금/MAG ⎵ 이것/NP+ㄴ/JX ⎵ 홍/NNP ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC+보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ㄴ데/EF+./SF
로텐더홀/NNG+에/JKB ⎵ 계시/VV+는/ETM ⎵ 의원/NNG+님/XSN+들/XSN+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VV+면/EC ⎵ 한국/NNP+당/NNG+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+아서/EC ⎵ 이야기하/VV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VA+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VA+는/ETM ⎵ 것/NNB+이/VCP+니까/EC+,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련되/VV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요하/VA+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VCN+겠/EP+습니까/EF+./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+ㄹ까/EF+요/JX+?/SF
```


##### Kakao Khaiii
```text
◇/SW ⎵ 노영희/NNP+:/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VV+고요/EF+./SF
제/NP+가/JKS ⎵ 두/MM ⎵ 번/NNB+째/XSN ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF+./SF
그런데/MAJ ⎵ 사실/MAG ⎵ 4/SN++/SW+1/SN+이/JKS ⎵ 불법/NNG+성/XSN+이/JKS ⎵ 있/VV+냐/EC+,/SP ⎵ 없/VA+냐/EC ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치/NNG+하/XSV+고/EC+./SF
지금/MAG ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/SN++/SW+1/SN ⎵ 내/NNB+에서/JKB+도/JX ⎵ 사실/MAG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EC+,/SP ⎵ 이것/NP+이/JKS ⎵ 합의/NNG+가/JKS ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF+./SF
이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF+./SF
비례/NNG+대표/NNG ⎵ 50/SN+석/NNB+,/SP ⎵ 그리고/MAJ ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/SN+석/NNB+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정/NNG+하/XSV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC+,/SP ⎵ 문제/NNG+는/JX ⎵ '/SS+캡/SL+'/SS+(/SS+cap/SL+)/SS+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라는/ETM ⎵ 거/NNB+이/VCP+죠/EF+./SF
비례/NNG+대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+아서/EF+./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+여서/EC ⎵ 민/NNP+주/NNG+당하/XSV+고/EC ⎵ 정/NNP+의/NNG+당/NNP ⎵ 이런/MM ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+이/JKS ⎵ 완전히/MAG ⎵ 다/MM+르/VA+ㄴ/ETM ⎵ 것/NNB ⎵ 같/VA+아요/EF+./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제/NP+가/JKS ⎵ 지금/MAG ⎵ 이/NNP+것/NP+ㄴ/JX ⎵ 홍/NNP ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC+보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ㄴ데/EF+./SF
로텐더홀/NNP+에/JKB ⎵ 계시/VV+는/ETM ⎵ 의원/NNG+님/XSN+들/XSN+께/JKS+서/EC ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VV+면/EC ⎵ 한국당/NNP+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+아서/EC ⎵ 이야기/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VV+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VV+는/ETM ⎵ 거/NNB+이/VCP+니까/EC+,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VCN+겠/EP+습니까/EF+./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으/EP+실까요/EF+?/SF
```


##### 은전한닢(Mecab-ko)
```text
◇/SW ⎵ 노영희/NNP ⎵ :/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VV+고요/EF ⎵ ./SF
제/NP+가/JKS ⎵ 두/MM ⎵ 번/NNM+째/XSN ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF ⎵ ./SF
그런데/MAJ ⎵ 사실/MAG ⎵ 4/SN ⎵ +/SW ⎵ 1/SN ⎵ 이/NR ⎵ 불법/NNG+성/XSN+이/JKS ⎵ 있/VA+냐/EF ⎵ ,/SP ⎵ 없/VA+냐/EF ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치/NNG+하/XSV+고/EC ⎵ ./SF
지금/MAG ⎵ 중요/NNG+하/XSA+ᆫ/ETM ⎵ 것/NNB+은/JX ⎵ 4/SN ⎵ +/SW ⎵ 1/SN ⎵ 내/NP+에서/JKB+도/JX ⎵ 사실/MAG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EF ⎵ ,/SP ⎵ 이것/NP+이/JKS ⎵ 합의/NNG+가/JKS ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF ⎵ ./SF
이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF ⎵ ./SF
비례/NNG ⎵ 대표/NNG ⎵ 50/SN ⎵ 석/NNM ⎵ ,/SP ⎵ 그리고/MAJ ⎵ 지역/NNG+구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/SN ⎵ 석/NNM+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정/NNG+하/XSV+는/ETM ⎵ 크/VA+ᆫ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC ⎵ ,/SP ⎵ 문제/NNG+는/JX ⎵ '/SW ⎵ 캡/NNG ⎵ '/SW ⎵ (/SS ⎵ cap/SL ⎵ )/SS ⎵ 을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP ⎵ 라는/ETM ⎵ 거/NNB+이/VCP+죠/EF ⎵ ./SF
비례/NNG ⎵ 대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+아서/EC ⎵ ./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+아서/EC ⎵ 민주/NNG+당/NNG+하고/JC ⎵ 정의/NNG+당/NNG ⎵ 이런/MM ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+이/JKS ⎵ 완전히/MAG ⎵ 다르/VA+ᆫ/ETM ⎵ 것/NNB ⎵ 같/VA+아요/EF ⎵ ./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제/NP+가/JKS ⎵ 지금/MAG ⎵ 이거/NP+ㄴ/JX ⎵ 홍/NNG ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC ⎵ 보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ᆫ데/EC ⎵ ./SW ⎵ 로/JKB ⎵ 텐더/NNG ⎵ 홀/NNG+에/JKB ⎵ 계시/VV+는/ETM ⎵ 의원/NNG+님/XSN ⎵ 들/XSN+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VX+면/EC ⎵ 한국/NNP ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+서/EC ⎵ 이야기/NNG+하/XSV+ᆯ/ETM ⎵ 수/NNB ⎵ 있/VV+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ᆯ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VX+는/ETM ⎵ 거/NNB+이/VCP+니까/EC ⎵ ,/SP ⎵ 캡/MAG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ᆫ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요/NNG+하/XSA+ᆫ/ETM ⎵ 것/NNB ⎵ 아니/VCN+겠/EP+습니까/EF ⎵ ./SF
좀/MAG ⎵ 응하/VV+ᆯ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+시/EP+ᆯ까/EF+요/JX ⎵ ?/SF
```


##### 코모란
```text
◇/SW ⎵ 노/NNP+영희/NNP+:/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VX+고요/EF+./SF
제가/NNP ⎵ 두/MM ⎵ 번/NNB+째/XSN ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF+./SF
그런데/MAJ ⎵ 사실/NNG ⎵ 4/SN++/SW+1/SN+이/NNP ⎵ 불법/NNG+성/XSN+이/JKS ⎵ 있/VV+냐/EC+,/SP ⎵ 없/VA+냐/EC ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치/NNG+하/XSV+고/EF+./SF
지금/MAG ⎵ 중요/XR+하/XSA+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/SN++/SW+1/SN ⎵ 내/NP+에서/JKB+도/JX ⎵ 사실/NNG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EC+,/SP ⎵ 이/VV+게/EC ⎵ 합의/NNG+가/JKS ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF+./SF
이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF+./SF
비례/NNP+대표/NNP ⎵ 50/SN+석/NNB+,/SP ⎵ 그리고/MAJ ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/SN+석/NNB+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정/NNG+하/XSV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC+,/SP ⎵ 문제/NNG+는/JX ⎵ '/SS+캡/NNG+'/SS+(/SS+cap/SL+)/SS+을/ETM ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라는/ETM ⎵ 거/NNB+죠/EF+./SF
비례/NNP+대표/NNP ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+아서/EF+./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+아서/EC ⎵ 민주당/NNP+하고/JKB ⎵ 정의당/NNP ⎵ 이런/MM ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+이/JKS ⎵ 완전히/MAG ⎵ 다른/MM ⎵ 것/NNB ⎵ 같/VA+아요/EF+./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제가/NNP ⎵ 지금/MAG ⎵ 이건/NNP ⎵ 홍/NNP ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC+보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 걸/VV+ㄴ데/EF+./SF
로/NNG+텐/NNG+더/NNG+홀/NNG+에/JKB ⎵ 계시/VV+는/ETM ⎵ 의원/NNG+님/XSN+들/XSN+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VV+면/EC ⎵ 한국/NNP+당의/NNP ⎵ 입장/NNG+을/JKO ⎵ 가/VV+아서/EC ⎵ 이야기/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VV+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VV+는/ETM ⎵ 걸/VV+니까/EC+,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요/XR+하/XSA+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VCN+겠/EP+습니까/EF+./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+ㄹ까요/EF+?/SF
```


##### 꼬꼬마
```text
◇/SW ⎵ 노영희/NF+:/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VV+고요/EF+./SF
저/NP+가/JKS ⎵ 두/MM ⎵ 번째/NNB ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF+./SF
그런데/MAJ ⎵ 사실/NNG ⎵ 4/NR++/SW+1/NR ⎵ 이/MM ⎵ 불법/NNG+성/XSN+이/JKS ⎵ 있/VV+냐/EF+,/SP
없/VA+냐/EF
이/MM ⎵ 논의/NNG+는/JX ⎵ 차치/NNG+하/XSV+고/EC+./SF ⎵ 지금/MAG ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/NR++/SW+1/NR ⎵ 내/NNB+에서/JKB+도/JX ⎵ 사실/NNG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EC+,/SP ⎵ 이것/NP+이/JKS ⎵ 합의/NNG+가/JKS ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF+./SF
이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF+./SF
비례/NNG+대표/NNG ⎵ 50/NR+석/NNM+,/SP ⎵ 그리/MAG ⎵ 이/VCP+고/EC ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/NR+석/NNM+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정/NNG+하/XSV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC+,/SP ⎵ 문/NNG+제/XSN+는/JX ⎵ '/SS ⎵ 캐/VV+'/SS ⎵ (/SS+cap/SL+)/SS ⎵ 을/NNG ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라는/ETM ⎵ 것/NNB+이/VCP+죠/EF+./SF
비례/NNG+대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대/NNG+하/XSV+어서/EC+./SF ⎵ 그런데/MAJ ⎵ 캡을/NF ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대/NNG+하/XSV+어서/EC ⎵ 민주당/NNG+하/XSV+고/EC ⎵ 정의당/NNG ⎵ 이런/MM ⎵ 당의/NNG ⎵ 입장/NNG+이/JKS ⎵ 완전히/MAG ⎵ 다르/VA+ㄴ/ETM ⎵ 것/NNB ⎵ 같/VA+아요/EF+./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제/NP+가/JKS ⎵ 지금/NNG ⎵ 이/VCP+건/EC ⎵ 홍/NF ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC ⎵ 보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ㄴ데/EC+./SF ⎵ 로텐더홀/NF+에/JKB ⎵ 계시/VV+는/ETM ⎵ 의원/NNG+님/XSN+들/XSN+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VX+면/EC ⎵ 한국/NNG+당/XSN+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+아서/EC ⎵ 이야기/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VV+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VV+는/ETM ⎵ 걸/VV+니까/EC+,/SP ⎵ 캐/VV ⎵ 씌우는/NF ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VV+겠/EP+습니까/EF+./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+ㄹ까요/EF+?/SF
```


##### 아리랑 (루씬)
```text
◇/NNG ⎵ 노영희/NNG ⎵ :/SP ⎵ 거기까지/NNG ⎵ 일단/NNG ⎵ 하고요/NNG ⎵ ./SF
제가/NNG ⎵ 두/NNG ⎵ 번째/NNG ⎵ 질문/NNG ⎵ 하겠습니다/NNG ⎵ ./SF
그런데/NNG ⎵ 사실/NNG ⎵ 4+1이/NNG ⎵ 불법성이/NNG ⎵ 있냐/NNG ⎵ ,/SP ⎵ 없냐/NNG ⎵ 이/NNG ⎵ 논의는/NNG ⎵ 차치하고/NNG ⎵ ./SF
지금/NNG ⎵ 중요한/NNG ⎵ 것은/NNG ⎵ 4+1/NNG ⎵ 내에서도/NNG ⎵ 사실/NNG ⎵ 이야기가/NNG ⎵ 다르다/NNG ⎵ ,/SP ⎵ 이게/NNG ⎵ 합의가/NNG ⎵ 안/NNG ⎵ 됐다/NNG ⎵ ./SF
이/NNG ⎵ 이야기가/NNG ⎵ 나오고/NNG ⎵ 있/NNG ⎵ 습니다/NNG ⎵ ./SF
비례대표/NNG ⎵ 50석/NNG ⎵ ,/SP ⎵ 그리고/NNG ⎵ 지역구/NNG ⎵ 의원을/NNG ⎵ 250석으로/NNG ⎵ 하/VV+는/EF ⎵ 식/NNG+으로/JX ⎵ 조정/NNG+하/XSV+는/EF ⎵ 큰/NNG ⎵ 틀/NNG+에/JX ⎵ 서/NNG+의/JX ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EF ⎵ ,/SP ⎵ 문제/NNG+는/JX ⎵ '/SS ⎵ 캡/NNG ⎵ '/SS ⎵ (/SS ⎵ cap/NNG ⎵ )/SS ⎵ 을/NNG ⎵ 씌우/VV+는/EF ⎵ 방식/NNG+이/XSV+라는/EF ⎵ 거죠/NNG ⎵ ./SF
비례/NNG ⎵ 대표/NNG ⎵ 의석/NNG+에/JX ⎵ 대/NNG ⎵ 해서/NNG ⎵ ./SF
그런데/MAG ⎵ 캡/NNG+을/JX ⎵ 씌우/VV+는/EF ⎵ 방식/NNG+에/JX ⎵ 대하/VV+어서/EF ⎵ 민주/NNG+당하/XSV+고/EF ⎵ 정의/NNG ⎵ 당/NNG ⎵ 이런/MAG ⎵ 당/NNG+의/JX ⎵ 입장/NNG+이/JX ⎵ 완전히/MAG ⎵ 다른/NNG ⎵ 것/NNG ⎵ 같/VV+아요/EF ⎵ ./SF
그래서/MAG ⎵ 혹시/MAG ⎵ 제가/NNG ⎵ 지금/NNG ⎵ 이건 홍/NNG ⎵ 의원/NNG ⎵ 님/NNG+께/JX ⎵ 여쭈/VV+어/EC+보/VX+고/EF ⎵ 싶/VV+은/EF ⎵ 거/VV+ㄴ데/EF ⎵ ./SF
로텐더/NNG ⎵ 홀/NNG+에/JX ⎵ 계시/VV+는/EF ⎵ /NNG+의/JX ⎵ 원님/NNG ⎵ 들/NNG+께서/JX ⎵ 밖/NNG+에서/JX ⎵ 계속/NNG ⎵ 그렇/VV+게/EF ⎵ 계시/VV+면/EF ⎵ 한국/NNG ⎵ 당/NNG+의/JX ⎵ 입장/NNG+을/JX ⎵ 가/VV+어서/EF ⎵ 이야기/NNG+하/XSV+ㄹ/EF ⎵ 수/NNG ⎵ 있/VV+는/EF ⎵ 기회/NNG+가/JX ⎵ 아예/NNG ⎵ 사라지/VV+ㄹ/EF ⎵ 수도/NNG ⎵ 있/VV+는/EF ⎵ 거/VV+니까/EF ⎵ ,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/EF ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ㄴ/EF ⎵ 부분/NNG+도/JX ⎵ 중요/NNG+하/XSV+ㄴ/EF ⎵ 것/NNG ⎵ 아니/VV+겠/EP+습니까/EF ⎵ ./SF
좀/NNG ⎵ 응하/VV+ㄹ/EF ⎵ 생각/NNG+은/JX ⎵ 없/VV+으시/EP+ㄹ까요/EF ⎵ ?/SF
```


##### 한나눔
```text
◇/NNG ⎵ 노영희:/NNG ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VX+고요/EF ⎵ ./SF
저/NP+가/JKS ⎵ 두/NR ⎵ 번/NNM+째/XSN ⎵ 질문/NNG ⎵ 하/VX+겠/EP+습니다/EF ⎵ ./SF
그런데/MAJ ⎵ 사/VV+시/EP+ㄹ/ETM ⎵ 4/NR++1/NR+이/JKC ⎵ 불법/NNG+성/NNG+이/JKC ⎵ 있/VX+냐/EF+,/SP ⎵ 없/VA+냐/EF ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치/NNG+하고/JC ⎵ ./SF
지금/MAG ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/NR++1/NR ⎵ 내/NP+에서/JKB+도/JX ⎵ 사실/MAG ⎵ 이야기/NNG+가/JKC ⎵ 다르/VA+다/EF+,/SP ⎵ 이/NP+이/VCP+게/EC ⎵ 합의가/NNG ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF ⎵ ./SF
이/MM ⎵ 이야기/NNG+가/JKC ⎵ 나/VV+아/EC+오/VX+고/EC ⎵ 있/VX+습니다/EF ⎵ ./SF
비례대표/NNG ⎵ 50/NR+석/NNM+,/SP ⎵ 그리고/MAJ ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/NR+석/NNM+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정/NNG+하/XSV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합/NNG+의/JKG+는/JX ⎵ 되/VV+었/EP+는데/EC+,/SP ⎵ 문제/NNG+는/JX ⎵ '/SS+캡/NNG+'/SS+(/SS+cap/SL+)/SS+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라/EF+는/ETM ⎵ 거/NNG+이/VCP+죠/EF ⎵ ./SF
비례대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+어서/EC ⎵ ./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+어서/EC ⎵ 민주/NNG+당/NNG+하고/JC ⎵ 정의/NNG+당/NNG ⎵ 이런/MM ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+이/JKC ⎵ 완전히/MAG ⎵ 다른/MM ⎵ 것/NNB ⎵ 같/VA+아/EF+요/JX ⎵ ./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 저/NP+가/JKS ⎵ 지금/MAG ⎵ 이/NP+이/VCP+건/EC ⎵ 홍/NNG ⎵ 의원님/NNG+께/JKB ⎵ 여쭈/VV+어/EC+보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ㄴ데/EC ⎵ ./SF
로텐더홀/NNG+에/JKB ⎵ 계시/VA+는/ETM ⎵ 의원님/NNG+들/NNG+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VA+면/EC ⎵ 한국당/NNG+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+서/EC ⎵ 이야기/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VX+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VX+는/ETM ⎵ 것/NNB+이/VCP+니까/EC+,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VA+겠/EP+습니까/EF ⎵ ./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+ㄹ까/EF+요/JX ⎵ ?/SF
```


##### Open Korean Text
```text
◇/SL ⎵ 노/NNG+영희/NNG+:/SF ⎵ 거기/NNG+까지/JX ⎵ 일단/NNG ⎵ 하고요/VV+./SF
제/NNG+가/JX ⎵ 두/NNG ⎵ 번째/XSO ⎵ 질문/NNG ⎵ 하겠습니다/VV+./SF
그런데/JC ⎵ 사실/NNG ⎵ 4/NR++/SF+1/NR+이/NNG ⎵ 불법/NNG+성/XSO+이/JX ⎵ 있냐/VA+,/SF ⎵ 없냐/VA ⎵ 이/NNG ⎵ 논의/NNG+는/JX ⎵ 차치하고/VV+./SF
지금/NNG ⎵ 중요한/VA ⎵ 것/NNG+은/JX ⎵ 4/NR++/SF+1/NR ⎵ 내/NNG+에서도/JX ⎵ 사실/NNG ⎵ 이야기/NNG+가/JX ⎵ 다르다/VA+,/SF ⎵ 이/NNG+게/JX ⎵ 합의/NNG+가/JX ⎵ 안/NNG ⎵ 됐다/VV+./SF
이/NNG ⎵ 이야기/NNG+가/JX ⎵ 나오고/VV ⎵ 있습니다/VA+./SF
비례대표/NNG ⎵ 50/NR+석/NNG+,/SF ⎵ 그리고/JC ⎵ 지역구/NNG ⎵ 의원/NNG+을/JX ⎵ 250/NR+석/NNG+으로/JX ⎵ 하는/VV ⎵ 식/NNG+으로/JX ⎵ 조정/NNG+하는/VV ⎵ 큰/VV ⎵ 틀/NNG+에서의/JX ⎵ 합의/NNG+는/JX ⎵ 됐는데/VV+,/SF ⎵ 문제/NNG+는/JX ⎵ '/SF+캡/NNG+'(/SF+cap/SL+)/SF+을/JX ⎵ 씌우는/VV ⎵ 방식/NNG+이라는/JX ⎵ 거/NNG+죠/JX+./SF
비례대표/NNG ⎵ 의석/NNG+에/JX ⎵ 대해/NNG+서/JX+./SF
그런데/JC ⎵ 캡/NNG+을/JX ⎵ 씌우는/VV ⎵ 방식/NNG+에/JX ⎵ 대해/NNG+서/JX ⎵ 민주당/NNG+하고/JX ⎵ 정의당/NNG ⎵ 이런/VA ⎵ 당/NNG+의/JX ⎵ 입장/NNG+이/JX ⎵ 완전히/VA ⎵ 다른/NNG ⎵ 것/NNG ⎵ 같아요/VA+./SF
그래서/MAG ⎵ 혹시/NNG ⎵ 제/NNG+가/JX ⎵ 지금/NNG ⎵ 이/MM+것/NNG+은/JX ⎵ 홍/NNG ⎵ 의원님/NNG+께/JX ⎵ 여쭤/VV+보고/NNG ⎵ 싶은/VV ⎵ 거/NNG+인데/JX+./SF
로텐더홀/NNG+에/JX ⎵ 계시는/VA ⎵ 의원님/NNG+들/XSO+께서/JX ⎵ 밖/NNG+에서/JX ⎵ 계속/NNG ⎵ 그렇게/MAG ⎵ 계시/NNG+면/JX ⎵ 한국/NNG+당/XSO+의/JX ⎵ 입장/NNG+을/JX ⎵ 가서/VV ⎵ 이야기/NNG+할/VV ⎵ 수/NNG ⎵ 있는/VA ⎵ 기회/NNG+가/JX ⎵ 아예/NNG ⎵ 사라질/VV ⎵ 수도/NNG ⎵ 있는/VA ⎵ 거/NNG+니까/JX+,/SF ⎵ 캡/NNG ⎵ 씌우는/VV ⎵ 문제/NNG ⎵ 관련/NNG+된/VV ⎵ 부분/NNG+도/JX ⎵ 중요한/VA ⎵ 것/NNG ⎵ 아니겠습니까/VA+./SF
좀/NNG ⎵ 응/NNG+할/VV ⎵ 생각/NNG+은/JX ⎵ 없으실까/VA+요/NNG+?/SF
```


##### Daon
```text
◇/SP ⎵ 노영희/NNP+:/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VV+고요/EF+./SF
제/NP+가/JKS ⎵ 두/MM ⎵ 번째/NNB ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF+./SF
그런데/MAJ ⎵ 사실/NNG ⎵ 4/SN++/SW+1/SN+이/JKS ⎵ 불법/NNG+성/XSN+이/JKS ⎵ 있/VA+냐/EC+,/SP ⎵ 없/VA+냐/EC ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치/NNG+하/XSV+고/EC+./SF
지금/NNG ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/SN++/SW+1/SN ⎵ 내/NNB+에서/JKB+도/JX ⎵ 사실/NNG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EF+,/SP ⎵ 이것/NP+이/JKS ⎵ 합의/NNG+가/JKS ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EC ⎵ 이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF+./SF
비례대표/NNG ⎵ 50/SN+석/NNB+,/SP ⎵ 그리고/MAJ ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/SN+석/NNB+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정/NNG+하/XSV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC+,/SP ⎵ 문제/NNG+는/JX ⎵ '/SP+캡/NNG+'/SP+(/SP+cap/SL+)/SP+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라는/ETM ⎵ 거/NNB+이/VCP+죠/EF+./SF
비례대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+어서/EC+./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+어서/EC ⎵ 민주당/NNP+하/XSV+고/EC ⎵ 정의당/NNP ⎵ 이런/MM ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+이/JKS ⎵ 완전/NNG+히/EC ⎵ 다른/MM ⎵ 것/NNB ⎵ 같/VA+아요/EF+./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제/NP+가/JKS ⎵ 지금/MAG ⎵ 이것/NP+ㄴ/JX ⎵ 홍/NNP ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC+보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ㄴ데/EF+./SF
로/NNG+텐더/NNG+홀/NNG+에/JKB ⎵ 계시/VV+는/ETM ⎵ 의원/NNG+님/XSN+들/XSN+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VV+면/EC ⎵ 한국/NNP+당/XSN+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+아서/EC ⎵ 이야기/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VA+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VA+는/ETM ⎵ 거/NNB+이/VCP+니까/EC+,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요/NNG+하/XSA+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VCN+겠/EP+습니까/EF+./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+ㄹ까요/EF+?/SF
```


##### 라이노
```text
◇/SW ⎵ 노영희/NNP ⎵ :/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VV+고요/EF ⎵ ./SF
제/NP+가/JKS ⎵ 두/MM ⎵ 번/NNB+째/XSN ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF ⎵ ./SF
그런데/MAJ ⎵ 사실/MAG ⎵ 4/SN ⎵ +/SW ⎵ 1/SN+이/JKS ⎵ 불법/NNG+성/XSN+이/JKS ⎵ 있/VX+냐/EF ⎵ ,/SP ⎵ 없/VA+냐/EF ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치하/VV+고/EC ⎵ ./SF
지금/NNG ⎵ 중요하/VV+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/SN ⎵ +/SW ⎵ 1/SN ⎵ 내/NNG+에서/JKB+도/JX ⎵ 사실/MAG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EF ⎵ ,/SP ⎵ 이것/VV+이/JKS ⎵ 합의/NNG+가/JKS ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF ⎵ ./SF
이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF ⎵ ./SF
비례/NNG+대표/NNG ⎵ 50/SN+석/NNB ⎵ ,/SP ⎵ 그리고/MAJ ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/SN+석/NNB+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정하/VV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC ⎵ ,/SP ⎵ 문제/NNG+는/JX ⎵ '/SS ⎵ 캡/NNG ⎵ '/SS ⎵ (/SS ⎵ cap/SL ⎵ )/SS ⎵ 을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라는/ETM ⎵ 것/NNB+죠/EF ⎵ ./SF
비례/NNG+대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+아서/EC ⎵ ./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+아서/EC ⎵ 민주당/NNP+하/XSV+고/EC ⎵ 정의당/NNP ⎵ 이런/MM ⎵ 당의/NNG ⎵ 입장/NNG+이/JKS ⎵ 완전히/MAG ⎵ 다른/MM ⎵ 것/NNB ⎵ 같/VA+아요/EF ⎵ ./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제/NP+가/JKS ⎵ 지금/NNG ⎵ 이것/NP+은/JX ⎵ 홍/NNP ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC+보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ㄴ데/EC ⎵ ./SF
로/NNG+텐/NNG+더/MAG+홀/NNG+에/JKB ⎵ 계시/VV+는/JX ⎵ 의원/NNG+님/XSN+들/XSN+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇게/MAG ⎵ 계시/VV+면/EC ⎵ 한국당/NNP+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+서/JKB ⎵ 이야기하/VV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VX+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VX+는/ETM ⎵ 것/NNB+니까/EC ⎵ ,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련되/VV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요하/VV+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VCN+겠/EP+습니까/EF ⎵ ./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+ㄹ까요/EF ⎵ ?/SF
```


##### UTagger
```text
◇/SW ⎵ 노영희/NNP+:/SP ⎵ 거기/NP+까지/JX ⎵ 일단/MAG ⎵ 하/VV+고요/EF+./SF
제/NP+가/JKS ⎵ 두/MM ⎵ 번째/NNB ⎵ 질문/NNG ⎵ 하/VV+겠/EP+습니다/EF+./SF
그런데/MAJ ⎵ 사실/MAG ⎵ 4/SN++/SW+1/SN+이/JKS ⎵ 불법성/NNG+이/JKS ⎵ 있/VA+냐/EC+,/SP ⎵ 없/VA+냐/EC ⎵ 이/MM ⎵ 논의/NNG+는/JX ⎵ 차치하/VV+고/EF+./SF
지금/MAG ⎵ 중요하/VA+ㄴ/ETM ⎵ 것/NNB+은/JX ⎵ 4/SN++/SW+1/SN ⎵ 내/NNB+에서/JKB+도/JX ⎵ 사실/MAG ⎵ 이야기/NNG+가/JKS ⎵ 다르/VA+다/EC+,/SP ⎵ 이것/NP+이/JKS ⎵ 합의/NNG+가/JKC ⎵ 안/MAG ⎵ 되/VV+었/EP+다/EF+./SF
이/MM ⎵ 이야기/NNG+가/JKS ⎵ 나오/VV+고/EC ⎵ 있/VX+습니다/EF+./SF
비례대표/NNG ⎵ 50/SN+석/NNB+,/SP ⎵ 그리고/MAJ ⎵ 지역구/NNG ⎵ 의원/NNG+을/JKO ⎵ 250/SN+석/NNB+으로/JKB ⎵ 하/VV+는/ETM ⎵ 식/NNB+으로/JKB ⎵ 조정하/VV+는/ETM ⎵ 크/VA+ㄴ/ETM ⎵ 틀/NNG+에서/JKB+의/JKG ⎵ 합의/NNG+는/JX ⎵ 되/VV+었/EP+는데/EC+,/SP ⎵ 문제/NNG+는/JX ⎵ '/SS+캡/NNG+'/SS+(/SS+cap/SL+)/SS+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+이/VCP+라는/ETM ⎵ 것/NNB+이/VCP+죠/EF+./SF
비례대표/NNG ⎵ 의석/NNG+에/JKB ⎵ 대하/VV+여서/EF+./SF
그런데/MAJ ⎵ 캡/NNG+을/JKO ⎵ 씌우/VV+는/ETM ⎵ 방식/NNG+에/JKB ⎵ 대하/VV+여서/EC ⎵ 민주당/NNP+하고/JKB ⎵ 정의/NNG+당/NNG ⎵ 이런/MM ⎵ 당/NNG+의/JKG ⎵ 입장/NNG+이/JKS ⎵ 완전히/MAG ⎵ 다르/VA+ㄴ/ETM ⎵ 것/NNB ⎵ 같/VA+아요/EF+./SF
그래서/MAJ ⎵ 혹시/MAG ⎵ 제/NP+가/JKS ⎵ 지금/MAG ⎵ 이거/NP+ㄴ/JX ⎵ 홍/NNP ⎵ 의원/NNG+님/XSN+께/JKB ⎵ 여쭈/VV+어/EC+보/VX+고/EC ⎵ 싶/VX+은/ETM ⎵ 것/NNB+이/VCP+ㄴ데/EF+./SF
로텐/NNP+더홀/NNP+에/JKB ⎵ 계시/VV+는/ETM ⎵ 의원/NNG+님/XSN+들/XSN+께서/JKS ⎵ 밖/NNG+에서/JKB ⎵ 계속/MAG ⎵ 그렇/VA+게/EC ⎵ 계시/VX+면/EC ⎵ 한국당/NNP+의/JKG ⎵ 입장/NNG+을/JKO ⎵ 가/VV+아서/EC ⎵ 이야기하/VV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VA+는/ETM ⎵ 기회/NNG+가/JKS ⎵ 아예/MAG ⎵ 사라지/VV+ㄹ/ETM ⎵ 수/NNB+도/JX ⎵ 있/VA+는/ETM ⎵ 것/NNB+이/VCP+니까/EC+,/SP ⎵ 캡/NNG ⎵ 씌우/VV+는/ETM ⎵ 문제/NNG ⎵ 관련/NNG+되/XSV+ㄴ/ETM ⎵ 부분/NNG+도/JX ⎵ 중요하/VA+ㄴ/ETM ⎵ 것/NNB ⎵ 아니/VCN+겠/EP+습니까/EF+./SF
좀/MAG ⎵ 응하/VV+ㄹ/ETM ⎵ 생각/NNG+은/JX ⎵ 없/VA+으시/EP+ㄹ까/EC+요/JX+?/SF
```


### 문장 번호 #34

원본 문장:

> 재판부는 "주변 사람들이 낸 탄원서 내용이 진실이기를 바라고 피고인이 재판과정에서 보여준 여러 다짐이 진심이기를 기대한다"며 "생을 다할 때까지 참회하는 것이 맞다. 피고인에게 할 한가지 당부는 여성이 있기에 사람들이 존재할 수 있다는 것이다. 잊지 말고 노력해서 밝은 삶을 준비하라"고 당부했다.

#### 품사 분석

##### ETRI API
```text
재판부/NNG+는/JX ⎵ "/SS+주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바라/VV+고/EC ⎵ 피고인/NNG+이/JKS ⎵ 재판/NNG+과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 진심/NNG+이/VCP+기/ETN+를/JKO ⎵ 기대하/VV+ㄴ다/EF+"/SS+며/EC ⎵ "/SS+생/NNG+을/JKO ⎵ 다하/VV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회하/VV+는/ETM ⎵ 것/NNB+이/JKS ⎵ 맞/VV+다/EF+./SF
피고인/NNG+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한/MM+가지/NNB ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VA+기에/EC ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재하/VV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VA+다는/ETM ⎵ 것/NNB+이/VCP+다/EF+./SF
잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력하/VV+어서/EC ⎵ 밝/VA+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비하/VV+라/EF+"/SS+고/JKQ ⎵ 당부하/VV+었/EP+다/EF+./SF
```


##### Kakao Khaiii
```text
재판부/NNG+는/JX ⎵ "/SS+주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바라/VV+고/EC ⎵ 피고인/NNG+이/JKS ⎵ 재판/NNG+과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 진심/NNG+이/VCP+기/ETN+를/JKO ⎵ 기대/NNG+하/XSV+ㄴ다/EF+"/SS+며/EC ⎵ "/SS+생/NNG+을/JKO ⎵ 다/MAG+하/VV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하/XSV+는/ETM ⎵ 것/NNB+이/JKS ⎵ 맞/VV+다/EF+./SF ⎵ 피고인/NNG+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한/MM+가지/NNB ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VV+기에/EC ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VV+다는/ETM ⎵ 것/NNB+이/VCP+다/EF+./SF ⎵ 잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력/NNG+하/XSV+여서/EC ⎵ 밝/VA+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비/NNG+하/XSV+라/EC+"/SS+고/JKQ ⎵ 당부/NNG+하/XSV+였/EP+다/EF+./SF
```


##### 은전한닢(Mecab-ko)
```text
재판/NNG+부/NNG+는/JX ⎵ "/SW ⎵ 주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ᆫ/ETM ⎵ 탄원/NNG+서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP ⎵ 기/ETN+를/JKO ⎵ 바라/VV+고/EC ⎵ 피고/NNG+인/NNG+이/JKS ⎵ 재판/NNG ⎵ 과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ᆫ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 진심/NNG+이/VCP ⎵ 기/ETN+를/JKO ⎵ 기대/NNG+하/XSV+ᆫ다/EC ⎵ "/SW ⎵ 며/EC ⎵ "/SW ⎵ 생/NNG+을/JKO ⎵ 다/MAG ⎵ 하/VV+ᆯ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하/XSV+는/ETM ⎵ 것/NNB+이/JKS ⎵ 맞/VV+다/EF ⎵ ./SF ⎵ 피고/NNG+인/NNG+에게/JKB ⎵ 하/VV+ᆯ/ETM ⎵ 한/MM ⎵ 가지/NNM ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VV+기/ETN+에/JKB ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재/NNG+하/XSV+ᆯ/ETM ⎵ 수/NNB ⎵ 있/VV+다는/ETM ⎵ 것/NNB+이/VCP ⎵ 다/EF ⎵ ./SF ⎵ 잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력/NNG+하/XSV+아서/EC ⎵ 밝/VA+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비/NNG+하/XSV+라/EC ⎵ "/SW ⎵ 고/JKQ ⎵ 당부/NNG+하/XSV+았/EP+다/EF ⎵ ./SF
```


##### 코모란
```text
재판부/NNG+는/JX ⎵ "/SS+주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바라/VV+고/EC ⎵ 피고인/NNP+이/JKS ⎵ 재판/NNG+과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 지/VX+ㄴ/ETM+심이기/NNP+를/JKO ⎵ 기대/NNG+하/XSV+ㄴ다/EC+"/SS+이/VCP+며/EC ⎵ "/SS+생/NNG+을/JKO ⎵ 다/MAG+하/XSV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하/XSV+는/ETM ⎵ 것/NNB+이/JKS ⎵ 맞/VV+다/EF+./SF ⎵ 피고인/NNP+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한가지/NNG ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VV+기/ETN+에/JKB ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VV+다는/ETM ⎵ 것/NNB+이/VCP+다/EF+./SF ⎵ 잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력/NNG+하/XSV+아서/EC ⎵ 밝/VA+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비/NNG+하/XSV+라/EC+"/SS+고/JKQ ⎵ 당부/NNG+하/XSV+았/EP+다/EF+./SF
```


##### 꼬꼬마
```text
재판부/NNG+는/JX ⎵ "/SS ⎵ 주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바라/VV+고/EC ⎵ 피고인/NNG+이/JKS ⎵ 재판/NNG+과정/NNG+에서/JKB ⎵ 보이/VV+어/EC ⎵ 주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 진심/NNG+이/VCP+기/ETN+를/JKO ⎵ 기대/NNG+하/XSV+ㄴ다/EC ⎵ "/SS+며/JC ⎵ "/SS ⎵ 생/XPN+을/NNG ⎵ 다/MAG+하/XSV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하/XSV+는/ETM ⎵ 것/NNB+이/JX ⎵ 맞/VV+다/EF+./SF
피고인/NNG+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한가지/NNG ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VV+기에/EC ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VV+다는/ETM ⎵ 것/NNB+이/VCP+다/EF+./SF
잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력/NNG+하/XSV+어서/EC ⎵ 밝/VA+은/ETM ⎵ 살/VV+ㅁ/ETN+을/JKO ⎵ 준비/NNG+하/XSV+라/EC ⎵ "/SS+고/JC ⎵ 당부/NNG+하/XSV+었/EP+다/EF+./SF
```


##### 아리랑 (루씬)
```text
재/NNG ⎵ 판/NNG ⎵ 부/NNG+는/JX ⎵ "/SS ⎵ 주변/NNG ⎵ 사람/NNG ⎵ 들/NNG+이/JX ⎵ 낸/NNG ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JX ⎵ 진실/NNG+이/XSV+기/ETN+를/JX ⎵ 바/VV+라고/EF ⎵ 피고인/NNG+이/JX ⎵ 재판/NNG ⎵ 과정/NNG+에서/JX ⎵ 보여주/VV+ㄴ/EF ⎵ 여러/NNG ⎵ 다지/VV+ㅁ/ETN+이/JX ⎵ 진심/NNG+이/XSV+기/ETN+를/JX ⎵ 기대/NNG+하/XSV+ㄴ다/EF ⎵ "/SS ⎵ 며/NNG ⎵ "/SS ⎵ 생/NNG+을/JX ⎵ 다하/VV+ㄹ/EF ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하/XSV+는/EF ⎵ 것/NNG+이/JX ⎵ 맞/VV+다/EF ⎵ ./SF ⎵ 피고인/NNG+에게/JX ⎵ 할/NNG ⎵ 한가지/NNG ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JX ⎵ 있/VV+기/ETN+에/JX ⎵ 사람/NNG ⎵ 들/NNG+이/JX ⎵ 존재/NNG+하/XSV+ㄹ/EF ⎵ 수/NNG ⎵ 있/VV+다는/EF ⎵ 것/NNG+이/XSV+다/EF ⎵ ./SF ⎵ 잊/VV+지/EF ⎵ 말/VV+고/EF ⎵ 노력/NNG+하/XSV+어서/EF ⎵ 밝/VV+은/EF ⎵ 삶/VV+을/EF ⎵ 준비/NNG+하/XSV+어라/EF ⎵ "/SS ⎵ 고/NNG ⎵ 당부/NNG+하/XSV+었/EP+다/EF ⎵ ./NA
```


##### 한나눔
```text
재판부/NNG+는/JX ⎵ "/SS+주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKC ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바/NNB+이/VCP+라/EF+고/JKQ ⎵ 피고인/NNG+이/JKC ⎵ 재판과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다지/VV+ㅁ/ETN+이/JKS ⎵ 진심/NNG+이/VCP+기/ETN+를/JKO ⎵ 기대한다"며/NNG ⎵ "/SS+생/NP+을/JKO ⎵ 다하/VV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하/XSV+는/ETM ⎵ 것/NNB+이/JKC ⎵ 맞/VV+다/EF ⎵ ./SF
피고인/NNG+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한/NR+가지/NNM ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKC ⎵ 있/VX+기/ETN+에/JKB ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VX+다/EF+는/ETM ⎵ 것/NNB+이/VCP+다/EF ⎵ ./SF
잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력/NNG+하/XSV+어서/EC ⎵ 밝/VA+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비/NNG+하/XSV+라/EF+"/SS+고/JKQ ⎵ 당부/NNG+하/XSV+었/EP+다/EF ⎵ ./SF
```


##### Open Korean Text
```text
재판/NNG+부는/VV ⎵ "/SF+주변/NNG ⎵ 사람/NNG+들/XSO+이/JX ⎵ 낸/VV ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JX ⎵ 진실/NNG+이기/NNG+를/JX ⎵ 바라고/VV ⎵ 피고인/NNG+이/JX ⎵ 재판/NNG+과정/NNG+에서/JX ⎵ 보여준/VV ⎵ 여러/NNG ⎵ 다짐/NNG+이/JX ⎵ 진심/NNG+이기/NNG+를/JX ⎵ 기대한다/VA+"/SF+며/NNG ⎵ "/SF+생/NNG+을/JX ⎵ 다/MAG+할/VV ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하는/VV ⎵ 것/NNG+이/JX ⎵ 맞다/VV+./SF
피고인/NNG+에게/JX ⎵ 할/VV ⎵ 한가지/NNG ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JX ⎵ 있기에/VA ⎵ 사람/NNG+들/XSO+이/JX ⎵ 존재/NNG+할/VV ⎵ 수/NNG ⎵ 있다는/VA ⎵ 것/NNG+이다/JX+./SF
잊지/VV ⎵ 말고/JX ⎵ 노력/NNG+해서/VV ⎵ 밝은/VV ⎵ 삶/NNG+을/JX ⎵ 준비/NNG+하라/NNG+"/SF+고/NNG ⎵ 당부/NNG+했다/VV+./SF
```


##### Daon
```text
재판부/NNG+는/JX ⎵ "/SP+주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바라/VV+고/EC ⎵ 피고인/NNG+이/JKS ⎵ 재판과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 진심/NNG+이/VCP+기/ETN+를/JKO ⎵ 기대/NNG+하/XSV+ㄴ다/EF+"/SP+며/EC ⎵ "/SP+생/NNG+을/JKO ⎵ 다하/VV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회/NNG+하/XSV+는/ETM ⎵ 것/NNB+이/JKS ⎵ 맞/VV+다/EF+./SF ⎵ 피고인/NNG+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한가지/NNG ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VA+기에/EC ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재/NNG+하/XSV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VA+다는/ETM ⎵ 것/NNB+이/VCP+다/EF+./SF ⎵ 잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력/NNG+하/XSV+어서/EC ⎵ 밝/VA+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비/NNG+하/XSV+라/EF+"/SP+고/EC ⎵ 당부/NNG+하/XSV+었/EP+다/EF+./SF
```


##### 라이노
```text
재판부/NNG+는/JX ⎵ "/SS ⎵ 주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바/NNG+라고/EC ⎵ 피고인/NNG+이/JKS ⎵ 재판/NNG+과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 진심/NNG+이/VCP+기/ETN+를/JKO ⎵ 기대/VV+하/VX+ㄴ다/EF ⎵ "/SS ⎵ 며/EC ⎵ "/SS ⎵ 생/NNG+을/JKO ⎵ 다/MAG+하/VV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회하/VV+는/ETM ⎵ 것/NNB+이/JKS ⎵ 맞/VV+다/EF ⎵ ./SF ⎵ 피고인/NNG+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한/MM+가지/NNB ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VX+기/ETN+에/JKB ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재하/VV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VX+다는/ETM ⎵ 것/NNB+이다/EF ⎵ ./SF ⎵ 잊/VV+지/EC ⎵ 말/VV+고/EC ⎵ 노력하/VV+아서/EC ⎵ 밝/VV+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비하/VV+라/EC ⎵ "/SS ⎵ 고/JKQ ⎵ 당부하/VV+았/EP+다/EF ⎵ ./SF
```


##### UTagger
```text
재판부/NNG+는/JX ⎵ "/SS+주변/NNG ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 내/VV+ㄴ/ETM ⎵ 탄원서/NNG ⎵ 내용/NNG+이/JKS ⎵ 진실/NNG+이/VCP+기/ETN+를/JKO ⎵ 바라/VV+고/EC ⎵ 피고인/NNG+이/JKS ⎵ 재판/NNG+과정/NNG+에서/JKB ⎵ 보이/VV+어/EC+주/VX+ㄴ/ETM ⎵ 여러/MM ⎵ 다짐/NNG+이/JKS ⎵ 진심/NNG+이/VCP+기/ETN+를/JKO ⎵ 기대하/VV+ㄴ다/EF+"/SS+며/EC ⎵ "/SS+생/NNG+을/JKO ⎵ 다하/VV+ㄹ/ETM ⎵ 때/NNG+까지/JX ⎵ 참회하/VV+는/ETM ⎵ 것/NNB+이/JKS ⎵ 맞/VV+다/EF+./SF ⎵ 피고인/NNG+에게/JKB ⎵ 하/VV+ㄹ/ETM ⎵ 한/MM+가지/NNB ⎵ 당부/NNG+는/JX ⎵ 여성/NNG+이/JKS ⎵ 있/VA+기에/EC ⎵ 사람/NNG+들/XSN+이/JKS ⎵ 존재하/VV+ㄹ/ETM ⎵ 수/NNB ⎵ 있/VA+다는/ETM ⎵ 것/NNB+이/VCP+다/EF+./SF ⎵ 잊/VV+지/EC ⎵ 말/VX+고/EC ⎵ 노력하/VV+여서/EC ⎵ 밝/VA+은/ETM ⎵ 삶/NNG+을/JKO ⎵ 준비하/VV+라/EF+"/SS+고/JKQ ⎵ 당부하/VV+였/EP+다/EF+./SF
```


### 문장 번호 #40

원본 문장:

> 검찰은 "모욕의 정도가 중하고 약자인 여성 외국인에 대한 폭력을 행사해 죄질이 불량하다"며 "이미 동종 전과가 수차례 있고 누범기간 중 발생했지만 범행을 일부 부인하고 반성의 기미를 전혀 보이고 있지 않다"고 구형 이유를 밝혔다.

#### 품사 분석

##### ETRI API
```text
검찰/NNG+은/JX ⎵ "/SS+모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중하/VA+고/EC ⎵ 약자/NNG+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사하/VV+어/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량하/VA+다/EF+"/SS+며/EC ⎵ "/SS+이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수차례/NNG ⎵ 있/VA+고/EC ⎵ 누범/NNG+기간/NNG ⎵ 중/NNB ⎵ 발생하/VV+었/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인하/VV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EF+"/SS+고/JKQ ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF+./SF
```


##### Kakao Khaiii
```text
검찰/NNG+은/JX ⎵ "/SS+모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중하/VA+고/EC ⎵ 약자/NNG+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사/NNG+하/XSV+여/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량/NNG+하/XSA+다/EF+"/SS+며/EC ⎵ "/SS+이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수차례/NNG ⎵ 있/VV+고/EC ⎵ 누범/NNG+기간/NNG ⎵ 중/NNB ⎵ 발생/NNG+하/XSV+였/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인/NNG+하/XSV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EF+"/SS+고/JKQ ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF+./SF
```


##### 은전한닢(Mecab-ko)
```text
검찰/NNG+은/JX ⎵ "/SW ⎵ 모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중하/VA+고/EC ⎵ 약자/NNG+이/VCP+ᆫ/ETM ⎵ 여성/NNG ⎵ 외국/NNG+인/NNG+에/JKB ⎵ 대하/VV+ᆫ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사/NNG+하/XSV+아/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량/NNG+하/XSV+다/EC ⎵ "/SW ⎵ 며/EC ⎵ "/SW ⎵ 이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수/NNG+차례/NNG ⎵ 있/VV+고/EC ⎵ 누범/NNG ⎵ 기간/NNG ⎵ 중/NNB ⎵ 발생/NNG+하/XSV+았/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인/NNG+하/XSV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EC ⎵ "/SW ⎵ 고/JKQ ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF ⎵ ./SF
```


##### 코모란
```text
검찰/NNG+은/JX ⎵ "/SS+모욕/NNP+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중/NNB+하/XSA+고/EC ⎵ 약자/NNP+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사/NNG+하/XSV+아/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량/NNG+하/XSV+다/EC+"/SS+이/VCP+며/EC ⎵ "/SS+이미/MAG ⎵ 동종/NNG ⎵ 전과/NNP+가/JKS ⎵ 수차례/NNG ⎵ 있/VV+고/EC ⎵ 누범/NNP+기간/NNP ⎵ 중/NNB ⎵ 발생/NNG+하/XSV+았/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인/NNG+하/XSV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VV+지/EC ⎵ 않/VX+다/EC+"/SS+고/JKQ ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF+./SF
```


##### 꼬꼬마
```text
검찰/NNG+은/JX ⎵ "/SS ⎵ 모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중하/VA+고/EC ⎵ 약자/NNG+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국/NNG+인/XSN+에/JKB ⎵ 대/NNG+하/XSV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사/NNG+하/XSV+어/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량/NNG+하/XSV+다/EC ⎵ "/SS+며/JC ⎵ "/SS ⎵ 이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수/NNG ⎵ 차례/NNG ⎵ 있/VV+고/EC ⎵ 누범/NNG+기간/NNG ⎵ 중/NNB ⎵ 발생/NNG+하/XSV+었/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인/NNG+하/XSV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EC ⎵ "/SS+고/JC ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF+./SF
```


##### 아리랑 (루씬)
```text
검/NNG ⎵ 찰/NNG+은/JX ⎵ "/SS ⎵ 모욕/NNG+의/JX ⎵ 정도/NNG+가/JX ⎵ 중하/VV+고/EF ⎵ 약자/NNG+이/XSV+ㄴ/EF ⎵ 여성/NNG ⎵ 외국/NNG ⎵ 인/NNG+에/JX ⎵ 대한/NNG ⎵ 폭력/NNG+을/JX ⎵ 행사/NNG+하/XSV+어/EF ⎵ 죄질/NNG+이/JX ⎵ 불량/NNG+하/XSV+어다/EF ⎵ "/SS ⎵ 며/JX ⎵ "/SS ⎵ 이미/NNG ⎵ 동종/NNG ⎵ 전과/NNG+가/JX ⎵ 수/NNG ⎵ 차례/NNG ⎵ 있/VV+고/EF ⎵ 누범/NNG ⎵ 기간/NNG ⎵ 중/NNG ⎵ 발생/NNG ⎵ 하/VV+었/EP+지만/EF ⎵ 범행/NNG+을/JX ⎵ 일부/NNG ⎵ 부인/NNG+하/XSV+고/EF ⎵ 반성/NNG+의/JX ⎵ 기미/NNG+를/JX ⎵ 전혀/NNG ⎵ 보이/VV+고/EF ⎵ 있/VV+지/EF ⎵ 않/VV+다/EF ⎵ "/SS ⎵ 고/NNG ⎵ 구형/NNG ⎵ 이유/NNG+를/JX ⎵ 밝히/VV+었/EP+다/EF ⎵ ./NA
```


##### 한나눔
```text
검찰/NNG+은/JX ⎵ "/SS+모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중/NNB+하고/JC ⎵ 약자/NNG+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사/NNG+하/XSV+어/EC ⎵ 죄질/NNG+이/JKC ⎵ 불량하다"며/NNG ⎵ "/SS+이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수차례/NNG ⎵ 있/VX+고/EC ⎵ 누범기간/NNG ⎵ 중/NNB ⎵ 발생/NNG+하/XSV+었/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일/NR+부/NNM ⎵ 부인/NNG+하고/JC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EF+"/SS+고/JKQ ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF ⎵ ./SF
```


##### Open Korean Text
```text
검찰/NNG+은/JX ⎵ "/SF+모욕/NNG+의/JX ⎵ 정도/NNG+가/JX ⎵ 중하/NNG+고/JX ⎵ 약자/NNG+인/JX ⎵ 여성/NNG ⎵ 외국인/NNG+에/JX ⎵ 대한/NNG ⎵ 폭력/NNG+을/JX ⎵ 행사/NNG+해/VV ⎵ 죄질/NNG+이/JX ⎵ 불량하다/VA+"/SF+며/NNG ⎵ "/SF+이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JX ⎵ 수/MM+차례/NNG ⎵ 있고/VA ⎵ 누범/NNG+기간/NNG ⎵ 중/NNG ⎵ 발생/NNG+했지만/VV ⎵ 범행/NNG+을/JX ⎵ 일부/NNG ⎵ 부인/NNG+하고/JX ⎵ 반성/NNG+의/JX ⎵ 기미/NNG+를/JX ⎵ 전혀/NNG ⎵ 보이/NNG+고/JX ⎵ 있지/VA ⎵ 않다/VV+"/SF+고/NNG ⎵ 구형/NNG ⎵ 이유/NNG+를/JX ⎵ 밝혔다/VV+./SF
```


##### Daon
```text
검찰/NNG+은/JX ⎵ "/SP+모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중/NNG+하/XSV+고/EC ⎵ 약자/NNG+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사/NNG+하/XSV+어/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량/NNG+하/XSA+다/EF+"/SP+며/EC ⎵ "/SP+이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수차례/NNG ⎵ 있/VA+고/EC ⎵ 누범/NNG+기간/NNG ⎵ 중/NNB ⎵ 발생/NNG+하/XSV+었/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인/NNG+하/XSV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EF+"/SP+고/EC ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF+./SF
```


##### 라이노
```text
검찰/NNG+은/JX ⎵ "/SS ⎵ 모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중하/VA+고/EC ⎵ 약자/NNG+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국인/NNG+에/JKB ⎵ 대/NNB+하/VV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사하/VV+아/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량하/VV+다/EF ⎵ "/SS ⎵ 며/EC ⎵ "/SS ⎵ 이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수차례/NNG ⎵ 있/VX+고/EC ⎵ 누범/NNG+기간/NNG ⎵ 중/NNB ⎵ 발생하/VV+았/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인하/VV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EF ⎵ "/SS ⎵ 고/JKQ ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF ⎵ ./SF
```


##### UTagger
```text
검찰/NNG+은/JX ⎵ "/SS+모욕/NNG+의/JKG ⎵ 정도/NNG+가/JKS ⎵ 중하/VA+고/EC ⎵ 약자/NNG+이/VCP+ㄴ/ETM ⎵ 여성/NNG ⎵ 외국인/NNG+에/JKB ⎵ 대하/VV+ㄴ/ETM ⎵ 폭력/NNG+을/JKO ⎵ 행사하/VV+여/EC ⎵ 죄질/NNG+이/JKS ⎵ 불량하/VA+다/EF+"/SS+며/EC ⎵ "/SS+이미/MAG ⎵ 동종/NNG ⎵ 전과/NNG+가/JKS ⎵ 수차례/NNG ⎵ 있/VA+고/EC ⎵ 누범/NNG+기간/NNG ⎵ 중/NNB ⎵ 발생하/VV+였/EP+지만/EC ⎵ 범행/NNG+을/JKO ⎵ 일부/NNG ⎵ 부인하/VV+고/EC ⎵ 반성/NNG+의/JKG ⎵ 기미/NNG+를/JKO ⎵ 전혀/MAG ⎵ 보이/VV+고/EC ⎵ 있/VX+지/EC ⎵ 않/VX+다/EF+"/SS+고/JKQ ⎵ 구형/NNG ⎵ 이유/NNG+를/JKO ⎵ 밝히/VV+었/EP+다/EF+./SF
```

