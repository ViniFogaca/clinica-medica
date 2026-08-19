const API_URL = 'http://localhost:8080/pacientes';
// Buscar pacientes cadastrados
async function carregarPacientes() {
    try {
        const resposta = await fetch(API_URL);
        const pacientes = await resposta.json();
        
        const divLista = document.getElementById('listaPacientes');
        divLista.innerHTML = '';

        if (pacientes.length === 0) {
            divLista.innerHTML = '<p style="text-align:center; color:#666;">Nenhum paciente registrado no momento.</p>';
            return;
        }

        pacientes.forEach(p => {
            const badgeClass = p.possuiPlanoSaude ? 'badge plano-sim' : 'badge plano-nao';
            const badgeText = p.possuiPlanoSaude ? 'Com Plano' : 'Particular';

            const card = document.createElement('div');
            card.className = 'paciente-item';
            card.innerHTML = `
                <div class="paciente-info">
                    <h3>${p.nome} <span class="${badgeClass}">${badgeText}</span></h3>
                    <p><strong>Idade:</strong> ${p.idade} anos | <strong>Especialidade:</strong> ${p.especialidade.replace('_', ' ')}</p>
                </div>
                <div class="actions-group">
                    <button class="btn btn-sm btn-edit" onclick="prepararEdicao(${p.id}, '${p.nome}', '${p.especialidade}', ${p.idade}, ${p.possuiPlanoSaude})">Editar</button>
                    <button class="btn btn-sm btn-delete" onclick="deletarPaciente(${p.id})">Excluir</button>
                </div>
            `;
            divLista.appendChild(card);
        });
    } catch (erro) {
        console.error("Erro ao carregar pacientes", erro);
    }
}

// Salvar / Editar paciente
document.getElementById('formPaciente').addEventListener('submit', async function(event) {
    event.preventDefault();

    const id = document.getElementById('pacienteId').value;
    const paciente = {
        nome: document.getElementById('nome').value,
        especialidade: document.getElementById('especialidade').value,
        idade: parseInt(document.getElementById('idade').value),
        possuiPlanoSaude: document.getElementById('planoSaude').checked
    };

    const ehEdicao = id !== "";
    const url = ehEdicao ? `${API_URL}/${id}` : API_URL;
    const metodo = ehEdicao ? 'PUT' : 'POST';

    try {
        const resposta = await fetch(url, {
            method: metodo,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(paciente)
        });

        if (resposta.ok) {
            limparFormulario();
            carregarPacientes();
        } else {
            const erroTexto = await resposta.text();
            try {
                const erroJson = JSON.parse(erroTexto);
                alert('Erro: ' + (erroJson.message || 'Dados inválidos.'));
            } catch {
                alert('Erro: ' + erroTexto);
            }
        }
    } catch (erro) {
        alert('Erro de conexão com o servidor do Back-end.');
    }
});

// Deletar paciente
async function deletarPaciente(id) {
    if (confirm('Atenção: Tem certeza que deseja excluir o prontuário deste paciente?')) {
        try {
            const resposta = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
            if (resposta.ok) {
                carregarPacientes();
            } else {
                alert('Erro ao excluir o paciente.');
            }
        } catch (erro) {
            alert('Erro de conexão ao tentar excluir.');
        }
    }
}

// Preencher formulário para edição
function prepararEdicao(id, nome, especialidade, idade, possuiPlanoSaude) {
    document.getElementById('pacienteId').value = id;
    document.getElementById('nome').value = nome;
    document.getElementById('especialidade').value = especialidade;
    document.getElementById('idade').value = idade;
    document.getElementById('planoSaude').checked = possuiPlanoSaude;

    document.getElementById('tituloForm').innerText = "Editando Paciente";
    document.getElementById('btnSubmit').innerText = "Atualizar Prontuário";
    document.getElementById('btnCancelar').style.display = "block";
    
    document.getElementById('tituloForm').scrollIntoView({ behavior: 'smooth' });
}

// Resetar formulário
function limparFormulario() {
    document.getElementById('pacienteId').value = "";
    document.getElementById('formPaciente').reset();
    document.getElementById('tituloForm').innerText = "Novo Paciente";
    document.getElementById('btnSubmit').innerText = "Salvar Prontuário";
    document.getElementById('btnCancelar').style.display = "none";
}

// Executar ao abrir
carregarPacientes();