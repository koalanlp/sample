const {initialize} = require("koalanlp/Util");
const {Tagger} = require("koalanlp/proc");
const {EUNJEON} = require("koalanlp/API");
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let tagger;

async function processText(text){
    if (text == null || text.trim().length === 0) {
        rl.close();
        return;
    }

    let sentences = await tagger(text);
    sentences.forEach((sent, i) => {
        console.log(`===== Sentence #${i} =====`);
        console.log(sent.surfaceString());

        console.log("# Analysis Result");
        // console.log(sent.singleLineString());

        sent.forEach((word) => {
            process.stdout.write(`Word [${word.id}] ${word.surface} = `);

            word.forEach((morph) => {
                process.stdout.write(`${morph.surface}/${morph.tag} `);
            });
            process.stdout.write('\n');
        });
    });

    readInput();
}

function readInput(){
    rl.question("분석할 문장을 입력하세요>> ", processText);
}

/***********************
 * Main Execution Part *
 ***********************/
initialize({packages: {EUNJEON: 'LATEST'}})  //KMR, ETRI, EUNJEON, ARIRANG, DAON, RHINO, OKT, KKMA, HNN, KHAIII
    .then(() => {
        tagger = new Tagger(EUNJEON);
        readInput();
    }).catch((err) => console.error('Error occurred!', err));