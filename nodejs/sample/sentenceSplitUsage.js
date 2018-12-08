const {initialize} = require("koalanlp/Util");
const {SentenceSplitter} = require("koalanlp/proc");
const {OKT} = require("koalanlp/API");
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let splitter;

async function processText(text){
    if (text == null || text.trim().length === 0) {
        rl.close();
        return;
    }

    let sentences = await splitter(text);
    sentences.forEach((sent, i) => {
        console.log(`[${i}] ${sent}`);
    });

    readInput();
}

function readInput(){
    rl.question("분석할 문장을 입력하세요>> ", processText);
}

/***********************
 * Main Execution Part *
 ***********************/
initialize({packages: {OKT: 'LATEST'}})  //HNN
    .then(() => {
        splitter = new SentenceSplitter(OKT);
        readInput();
    }).catch((err) => console.error('Error occurred!', err));