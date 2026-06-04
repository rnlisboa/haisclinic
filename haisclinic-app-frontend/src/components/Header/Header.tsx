import { Link, NavLink } from 'react-router-dom'
import logoHaisClinic from '../../assets/logo-haisclinic.png'

export function Header() {
  return (
    <header className="fixed left-1/2 top-9 z-50 flex h-36 w-[calc(100%-32px)] max-w-[1600px] -translate-x-1/2 items-center justify-between rounded bg-secondary px-9 shadow-sm">
      <Link to="/" aria-label="HaisClinic" className="shrink-0">
        <img
          src={logoHaisClinic}
          alt="HaisClinic"
          className="h-[47px] w-[281px] object-contain"
        />
      </Link>

      <nav aria-label="Navegação principal" className="flex items-center gap-8">
        <NavLink
          to="/cadastros"
          className={({ isActive }) =>
            `text-2xl text-white transition hover:text-button ${
              isActive ? 'font-extrabold' : 'font-semibold'
            }`
          }
        >
          Cadastros
        </NavLink>
        <NavLink
          to="/pacientes"
          className={({ isActive }) =>
            `text-2xl text-white transition hover:text-button ${
              isActive ? 'font-extrabold' : 'font-semibold'
            }`
          }
        >
          Pacientes
        </NavLink>
      </nav>
    </header>
  )
}
