import * as Label from '@radix-ui/react-label'
import type { InputHTMLAttributes } from 'react'

interface FormInputProps
  extends Omit<InputHTMLAttributes<HTMLInputElement>, 'onChange'> {
  label: string
  onChange: (value: string) => void
}

export function FormInput({
  label,
  name,
  value,
  type = 'text',
  onChange,
  className = '',
  ...props
}: FormInputProps) {
  return (
    <div className="block text-sm font-medium text-white">
      <Label.Root htmlFor={name}>{label}</Label.Root>
      <input
        id={name}
        name={name}
        type={type}
        value={value}
        onChange={(event) => onChange(event.target.value)}
        className={`mt-1 h-12 w-full rounded border border-transparent bg-white px-3 text-lg text-primary outline-none transition focus:border-button focus:ring-2 focus:ring-button ${className}`}
        {...props}
      />
    </div>
  )
}
