export function calcularIdade(dataNascimento: string) {
  const nascimento = new Date(`${dataNascimento}T00:00:00`)
  const hoje = new Date()
  let idade = hoje.getFullYear() - nascimento.getFullYear()
  const antesDoMes = hoje.getMonth() < nascimento.getMonth()
  const antesDoDia =
    hoje.getMonth() === nascimento.getMonth() &&
    hoje.getDate() < nascimento.getDate()

  if (antesDoMes || antesDoDia) {
    idade -= 1
  }

  return idade
}

export function formatarData(data: string) {
  return new Intl.DateTimeFormat('pt-BR', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
  }).format(new Date(data))
}
