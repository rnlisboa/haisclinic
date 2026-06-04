import * as Label from '@radix-ui/react-label'
import * as Select from '@radix-ui/react-select'
import { FiChevronDown } from 'react-icons/fi'

interface FormSelectOption {
  label: string
  value: string
}

interface FormSelectProps {
  label: string
  name: string
  value: string
  placeholder?: string
  options: FormSelectOption[]
  onChange: (value: string) => void
}

export function FormSelect({
  label,
  name,
  value,
  placeholder = 'Selecione',
  options,
  onChange,
}: FormSelectProps) {
  return (
    <div className="block text-sm font-medium text-white">
      <Label.Root htmlFor={name}>{label}</Label.Root>
      <Select.Root value={value} onValueChange={onChange}>
        <Select.Trigger
          id={name}
          className="mt-1 flex h-12 w-full items-center justify-between rounded border border-transparent bg-white px-3 text-lg text-primary outline-none transition focus:border-button focus:ring-2 focus:ring-button"
        >
          <Select.Value placeholder={placeholder} />
          <Select.Icon>
            <FiChevronDown aria-hidden />
          </Select.Icon>
        </Select.Trigger>

        <Select.Portal>
          <Select.Content className="z-[90] overflow-hidden rounded bg-white text-primary shadow-xl">
            <Select.Viewport className="p-1">
              {options.map((option) => (
                <Select.Item
                  key={option.value}
                  value={option.value}
                  className="cursor-pointer rounded px-3 py-2 text-base outline-none transition hover:bg-button/20 focus:bg-button/20"
                >
                  <Select.ItemText>{option.label}</Select.ItemText>
                </Select.Item>
              ))}
            </Select.Viewport>
          </Select.Content>
        </Select.Portal>
      </Select.Root>
    </div>
  )
}
