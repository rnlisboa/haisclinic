import type { AxiosPromise } from 'axios'
import type { CriarEvolucaoDto, EvolucaoDto } from '../dtos/evolucao.dto'
import type { CriarProntuarioDto, ProntuarioDto } from '../dtos/prontuario.dto'
import { Api, SERVICE } from './apiService/apiConfig'

class ProntuarioService {
  private apiCore: Api
  private baseUrl: string

  constructor() {
    this.apiCore = new Api()
    this.baseUrl = SERVICE.CORE
  }

  public async buscarProntuariosPorPacienteId(
    pacienteId: string,
  ): AxiosPromise<ProntuarioDto[]> {
    return await this.apiCore.axios.get(
      `${this.baseUrl}/prontuarios/paciente/${pacienteId}`,
    )
  }

  public async criarProntuario(
    prontuario: CriarProntuarioDto,
  ): AxiosPromise<ProntuarioDto> {
    return await this.apiCore.axios.post(`${this.baseUrl}/prontuarios`, prontuario)
  }

  public async buscarEvolucoesPorProntuarioId(
    prontuarioId: string,
  ): AxiosPromise<EvolucaoDto[]> {
    return await this.apiCore.axios.get(
      `${this.baseUrl}/paciente-evolucoes/prontuario/${prontuarioId}`,
    )
  }

  public async criarEvolucao(
    evolucao: CriarEvolucaoDto,
  ): AxiosPromise<EvolucaoDto> {
    return await this.apiCore.axios.post(
      `${this.baseUrl}/paciente-evolucoes`,
      evolucao,
    )
  }
}

export default ProntuarioService
