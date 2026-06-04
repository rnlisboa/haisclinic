interface Option<T extends string> {
  label: string
  value: T
}

interface FormTypeSelectorProps<T extends string> {
  value: T
  options: Option<T>[]
  onChange: (value: T) => void
}

export function FormTypeSelector<T extends string>({
  value,
  options,
  onChange,
}: FormTypeSelectorProps<T>) {
  return (
    <div className="flex items-center justify-center gap-6 text-2xl">
      {options.map((option, index) => (
        <div key={option.value} className="flex items-center gap-6">
          {index > 0 && <span className="h-8 w-px bg-white" aria-hidden />}
          <button
            type="button"
            onClick={() => onChange(option.value)}
            className={`transition hover:text-button ${
              value === option.value ? 'font-extrabold' : 'font-normal'
            }`}
          >
            {option.label}
          </button>
        </div>
      ))}
    </div>
  )
}
