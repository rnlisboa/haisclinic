import type { AxiosPromise } from 'axios'
import type { CriarPacienteDto } from '../../../dtos/paciente.dto'
import { Api, SERVICE } from '../../../services/apiService/apiConfig'

interface PacienteResponse {
  id: string
  nome: string
  sobrenome: string
  dataNascimento: string
  ativo: boolean
  email: string
  observacoes: string
  criadoEm: string
  atualizadoEm: string
  foto: string | null
}

type CriarPacienteRequest = Omit<CriarPacienteDto, 'foto'> & {
  foto?: string | null
}

type AtualizarPacienteRequest = Partial<CriarPacienteRequest> & {
  ativo?: boolean
}

class PacienteService {
  private apiCore: Api
  private baseUrl: string

  constructor() {
    this.apiCore = new Api()
    this.baseUrl = SERVICE.CORE
  }

  public async criarPaciente(
    paciente: CriarPacienteRequest,
  ): AxiosPromise<PacienteResponse> {
    return await this.apiCore.axios.post(`${this.baseUrl}/pacientes`, paciente)
  }

  public async buscarPacientes(): AxiosPromise<PacienteResponse[]> {
    return await this.apiCore.axios.get(`${this.baseUrl}/pacientes`)
  }

  public async buscarPacientePorId(id: string): AxiosPromise<PacienteResponse> {
    return await this.apiCore.axios.get(`${this.baseUrl}/pacientes/${id}`)
  }

  public async atualizarPaciente(
    id: string,
    paciente: AtualizarPacienteRequest,
  ): AxiosPromise<PacienteResponse> {
    return await this.apiCore.axios.put(`${this.baseUrl}/pacientes/${id}`, paciente)
  }

  public async deletarPaciente(id: string): AxiosPromise<void> {
    return await this.apiCore.axios.delete(`${this.baseUrl}/pacientes/${id}`)
  }

  public async salvarFotoPaciente(
    id: string,
    file: File,
  ): AxiosPromise<PacienteResponse> {
    const formData = new FormData()
    formData.append('file', file)

    return await this.apiCore.axios.post(
      `${this.baseUrl}/pacientes/${id}/foto`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      },
    )
  }
}

export default PacienteService
