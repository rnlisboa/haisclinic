import { Slot } from '@radix-ui/react-slot'
import type { ButtonHTMLAttributes, ReactNode } from 'react'

interface ButtonProps extends ButtonHTMLAttributes<HTMLButtonElement> {
  asChild?: boolean
  icon?: ReactNode
}

export function Button({ asChild, className = '', icon, children, ...props }: ButtonProps) {
  const Comp = asChild ? Slot : 'button'

  return (
    <Comp
      className={`inline-flex h-10 items-center justify-center gap-2 rounded bg-button px-4 text-sm font-semibold text-primary transition hover:brightness-95 focus:outline-none focus:ring-2 focus:ring-button focus:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-60 ${className}`}
      {...props}
    >
      {icon}
      {children}
    </Comp>
  )
}
