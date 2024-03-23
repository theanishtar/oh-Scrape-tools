function scrapeData() {
  const url = document.getElementById('urlInput').value;

  fetch(url)
    .then(response => response.text())
    .then(data => {
      const parser = new DOMParser();
      const htmlDocument = parser.parseFromString(data, 'text/html');

      const elementsWithAttribute = htmlDocument.querySelectorAll('[data-visualcompletion="ignore-dynamic"]');
      const output = document.getElementById('output');

      let result = '';
      elementsWithAttribute.forEach(element => {
        result += element.outerHTML + '\n';
      });

      output.textContent = result;
    })
    .catch(error => console.error('Error:', error));
}
