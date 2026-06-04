import type { AxiosPromise } from 'axios'
import type { CriarPacienteDto, PacienteDto } from '../dtos/paciente.dto'
import { Api, SERVICE } from './apiService/apiConfig'

type AtualizarPacienteRequest = Partial<Omit<CriarPacienteDto, 'foto'>> & {
  foto?: string | null
  ativo?: boolean
}

class PacienteService {
  private apiCore: Api
  private baseUrl: string

  constructor() {
    this.apiCore = new Api()
    this.baseUrl = SERVICE.CORE
  }

  public async buscarPacientePorId(id: string): AxiosPromise<PacienteDto> {
    return await this.apiCore.axios.get(`${this.baseUrl}/pacientes/${id}`)
  }

  public async buscarPacientes(): AxiosPromise<PacienteDto[]> {
    return await this.apiCore.axios.get(`${this.baseUrl}/pacientes`)
  }

  public async atualizarPaciente(
    id: string,
    paciente: AtualizarPacienteRequest,
  ): AxiosPromise<PacienteDto> {
    return await this.apiCore.axios.put(`${this.baseUrl}/pacientes/${id}`, paciente)
  }

  public async salvarFotoPaciente(id: string, file: File): AxiosPromise<PacienteDto> {
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

  public getFotoUrl(foto?: string | null): string | null {
    if (!foto) {
      return null
    }

    if (foto.startsWith('http')) {
      return foto
    }

    return `${this.baseUrl}${foto}`
  }
}

export default PacienteService
