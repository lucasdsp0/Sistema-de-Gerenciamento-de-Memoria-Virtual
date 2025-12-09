
# 🧠 Simulador de Gerenciamento de Memória Virtual

Este projeto consiste na implementação de um simulador de sistema de gerenciamento de **Memória Virtual** utilizando **Paginação por Demanda**. 
O software simula o comportamento da memória RAM e do Disco (Swap), comparando o desempenho de diferentes algoritmos de substituição de páginas.


## ✒️ Autores

- Lucas de Souza Pereira - Desenvolvimento e Documentação
- Arthur Bernardo Pazzutti da Silva - Desenvolvimento e Apresentação
- Caua Homero Goncalves Rodrigues - Desenvolvimento e Apresentação

Desenvolvido como Trabalho Prático para a disciplina de **Sistemas Operacionais**.

## 🚀 Funcionalidades

O simulador processa arquivos de entrada contendo configurações de hardware e sequências de acesso à memória, executando as seguintes políticas de substituição:

* **FIFO** (First-In, First-Out): Remove a página mais antiga.
* **LRU** (Least Recently Used): Remove a página menos recentemente utilizada.
* **RAND** (Random): Remove uma página aleatória.
* **MIN/OPT** (Ótimo): Remove a página que demorará mais tempo para ser usada novamente (algoritmo teórico).

## 📂 Estrutura do Projeto

O código foi organizado seguindo princípios **SOLID** e arquitetura em camadas:

```text
SimuladorSO/
├── src/                    # Código Fonte Java
│   ├── Main.java           # Ponto de entrada (CLI)
│   ├── dominio/            # Classes de dados (Configuracao, Resultado)
│   ├── logica/             # Núcleo da simulação (GerenciadorMemoria)
│   └── politicas/          # Implementação dos algoritmos (Strategy Pattern)
│
├── inputs/                 # Arquivos de teste (.txt)
├── bin/                    # Binários compilados (.class) - gerado automaticamente
├── gen.py                  # Script Python para gerar novos casos de teste
└── README.md               # Documentação
````

## 🛠️ Pré-requisitos

  * **Java JDK** (versão 8 ou superior).
  * **Python 3** (opcional, apenas para gerar novos testes).

## ⚙️ Compilação e Execução

Como o projeto utiliza **pacotes (packages)**, a compilação e execução devem ser feitas a partir da **raiz do projeto**.

### 1\. Compilar o Código

Abra o terminal na pasta raiz e execute:

```bash
# Cria a pasta bin (se não existir) e compila os arquivos
mkdir -p bin
javac -d bin -sourcepath src src/Main.java
```

### 2\. Executar o Simulador

O simulador lê os dados da entrada padrão (`stdin`). Utilize o redirecionamento de arquivos para passar os casos de teste.

**No Windows (PowerShell):**

```powershell
Get-Content inputs/small.txt | java -cp bin Main
```

**No Linux / macOS / CMD:**

```bash
java -cp bin Main < inputs/small.txt
```

> **Nota:** Para salvar o resultado em um arquivo, adicione `> saida.txt` ao final do comando.

-----

## 🧪 Gerando Novos Testes

O projeto inclui um script auxiliar `gen.py` para criar cenários de teste personalizados.

**Exemplo de uso:**

```bash
# Gera um teste com 5 sequências e salva em inputs/meu_teste.txt
python gen.py -s 5 > inputs/meu_teste.txt
```

**Opções do script:**

  * `-p`: Número de páginas virtuais.
  * `-s`: Número de sequências.
  * `--min_req` / `--max_req`: Mínimo e máximo de requisições por sequência.

-----

## 🧩 Decisões de Implementação (Entrevista)

Para fins de avaliação, as seguintes estruturas de dados e padrões foram utilizados:

1.  **Padrão Strategy (Interface `PoliticaSubstituicao`)**:
      * Permite trocar o algoritmo de substituição (FIFO, LRU, etc.) sem alterar o código do gerenciador de memória.
2.  **FIFO**: Utiliza uma `Queue` (LinkedList) para manter a ordem exata de chegada.
3.  **LRU**: Utiliza um `LinkedHashMap` com *accessOrder* ativado. Isso move automaticamente elementos acessados para o final da fila, mantendo o LRU (menos usado) no topo com custo O(1).
4.  **RAND**: Utiliza uma `ArrayList` auxiliar para permitir acesso aleatório rápido aos frames.
5.  **Memória Física**: Representada por um `Set` (HashSet) para garantir buscas de página (Hits) em tempo constante O(1).

-----
