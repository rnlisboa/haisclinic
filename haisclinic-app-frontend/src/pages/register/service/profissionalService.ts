import type { AxiosPromise } from 'axios'
import type { CriarProfissionalDto } from '../../../dtos/profissional.dto'
import { Api, SERVICE } from '../../../services/apiService/apiConfig'

interface ProfissionalResponse {
  id: string
  nome: string
  sobrenome: string
  especialidade: string
  email: string
  criadoEm: string
  atualizadoEm: string
  foto: string | null
}

type CriarProfissionalRequest = Omit<CriarProfissionalDto, 'foto'> & {
  foto?: string | null
}

type AtualizarProfissionalRequest = Partial<CriarProfissionalRequest>

class ProfissionalService {
  private apiCore: Api
  private baseUrl: string

  constructor() {
    this.apiCore = new Api()
    this.baseUrl = SERVICE.CORE
  }

  public async criarProfissional(
    profissional: CriarProfissionalRequest,
  ): AxiosPromise<ProfissionalResponse> {
    return await this.apiCore.axios.post(
      `${this.baseUrl}/profissionais`,
      profissional,
    )
  }

  public async buscarProfissionais(): AxiosPromise<ProfissionalResponse[]> {
    return await this.apiCore.axios.get(`${this.baseUrl}/profissionais`)
  }

  public async buscarProfissionalPorId(
    id: string,
  ): AxiosPromise<ProfissionalResponse> {
    return await this.apiCore.axios.get(`${this.baseUrl}/profissionais/${id}`)
  }

  public async atualizarProfissional(
    id: string,
    profissional: AtualizarProfissionalRequest,
  ): AxiosPromise<ProfissionalResponse> {
    return await this.apiCore.axios.put(
      `${this.baseUrl}/profissionais/${id}`,
      profissional,
    )
  }

  public async deletarProfissional(id: string): AxiosPromise<void> {
    return await this.apiCore.axios.delete(`${this.baseUrl}/profissionais/${id}`)
  }

  public async salvarFotoProfissional(
    id: string,
    file: File,
  ): AxiosPromise<ProfissionalResponse> {
    const formData = new FormData()
    formData.append('file', file)

    return await this.apiCore.axios.post(
      `${this.baseUrl}/profissionais/${id}/foto`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      },
    )
  }
}

export default ProfissionalService
