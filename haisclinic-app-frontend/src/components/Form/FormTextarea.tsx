import * as Label from '@radix-ui/react-label'
import type { TextareaHTMLAttributes } from 'react'

interface FormTextareaProps
  extends Omit<TextareaHTMLAttributes<HTMLTextAreaElement>, 'onChange'> {
  label: string
  onChange: (value: string) => void
}

export function FormTextarea({
  label,
  name,
  value,
  onChange,
  className = '',
  ...props
}: FormTextareaProps) {
  return (
    <div className="block text-sm font-medium text-white">
      <Label.Root htmlFor={name}>{label}</Label.Root>
      <textarea
        id={name}
        name={name}
        value={value}
        onChange={(event) => onChange(event.target.value)}
        className={`mt-1 min-h-24 w-full resize-none rounded border border-transparent bg-white px-3 py-2 text-lg text-primary outline-none transition focus:border-button focus:ring-2 focus:ring-button ${className}`}
        {...props}
      />
    </div>
  )
}
