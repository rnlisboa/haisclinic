import type { FormHTMLAttributes, ReactNode } from 'react'

interface FormContainerProps extends FormHTMLAttributes<HTMLFormElement> {
  children: ReactNode
}

export function FormContainer({ children, className = '', ...props }: FormContainerProps) {
  return (
    <form className={`mt-12 flex flex-1 flex-col ${className}`} {...props}>
      {children}
    </form>
  )
}
