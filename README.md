# Exploring Mars Project

Este projeto consiste em um simples ambiente para movimentação de sonda fictícia por meio de uma API REST. O funcionamento e os requisitos necessários são encontrados em: [Elo7](https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c).

## API REST

A API desenvolvida possibilita a criação e exclusão de campo (field) e a criação e execução de novas sondas (probes). A primeira chamada deve, obrigatoriamente, ser para o endpoint que cria o campo novo. Caso tente executar uma exploração antes de o campo ser definido, receberá o HTTP status PRECONDITION_FAILED.

### Endpoints
1. POST: /field
A posição (X,Y) do canto superior direito deverá ser enviada, em JSON, conforme o formato:
```
{
	"upperRightX": 5,
	"upperRightY": 5
}
```
2. DELETE: /field
Apaga o campo existente.

3. POST: /probes
Recebe uma lista com as informações de cada uma das sondas. O requisito era para duas sondas somente, mas o programa foi desenvolvido para suportar um número abitrário. Caso a posição de início da sonda já esteja ocupada, ela retornará a posição (-1, -1, N) como saída.

```
[
	{
		"name": "Probe 1",
 		"initialPosition": {
			"x": 1,
			"y": 2,
			"cardinalDirection": "N"
		},
		"commands": [
			"L", "M", "L", "M", "L", "M", "L", "M", "M"
		]
	},
	{
		"name": "Probe 2",
 		"initialPosition": {
			"x": 3,
			"y": 3,
			"cardinalDirection": "E"
		},
		"commands": [
			"M", "M", "R", "M", "M", "R", "M", "R", "R", "M"
		]
	}
]
```

### Considerações

O programa aceita um número arbitrário de sondas, e pode ter suas funções expandidas com facilidade. Há também uma resolução de colisões simples, documentada em testes. Quando há uma colisão para um passo intermediário, o programa lê os comandos seguintes, até o próximo M (move), para identificar se há possibilidade de ir àquela posição. Caso não seja possível, é mantida a posição atual da sonda.
Há disponível também a [Collection do Postman](https://www.getpostman.com/collections/e54e3c028cfdc22769cb) criada com as chamadas à API.




