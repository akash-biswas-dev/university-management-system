import {
  Component,
  computed,
  input,
  InputSignal,
  output,
  OutputEmitterRef,
  Signal,
  signal,
  WritableSignal
} from '@angular/core';
import {Eye, EyeOff, LucideAngularModule, LucideIconData} from 'lucide-angular';
import {AbstractControl, FormControl, ReactiveFormsModule} from '@angular/forms';


@Component({
  selector: 'app-input',
  templateUrl: 'input.component.html',
  imports: [
    LucideAngularModule,
    ReactiveFormsModule
  ],
  standalone: true
})
export class Input{


  protected readonly EyeOff = EyeOff;
  protected readonly Eye = Eye;

  label:InputSignal<string|undefined> = input<string|undefined>(undefined);
  placeholder:InputSignal<string> = input<string>('');
  inputType:InputSignal<InputType> = input<InputType>('text');
  size:InputSignal<InputSize> = input<InputSize>('md');
  variant:InputSignal<InputVariant> = input<InputVariant>('default');
  focused:InputSignal<boolean> = input<boolean>(false);
  error:InputSignal<string|undefined>= input<string| undefined>(undefined);
  required:InputSignal<boolean> = input<boolean>(false);
  leftIcon:InputSignal<LucideIconData|undefined> = input<LucideIconData|undefined>(undefined);
  rightIcon:InputSignal<LucideIconData|undefined> = input<LucideIconData|undefined>(undefined);
  showPasswordToggle:InputSignal<boolean> = input<boolean>(false);
  fullWidth:InputSignal<boolean> = input<boolean>(false);
  classes:InputSignal<string> = input<string>('');
  name:InputSignal<string> = input<string>('');
  helper:InputSignal<string | undefined> = input<string|undefined>('');
  formControlVal:InputSignal<FormControl<any>> = input<FormControl<any>>(new FormControl());

  onFocus:OutputEmitterRef<string> = output<string>();
  onBlur:OutputEmitterRef<string> = output<string>();

  baseClasses:string = `
    w-full transition-all duration-300 focus:outline-none
    disabled:opacity-50 disabled:cursor-not-allowed
    placeholder:text-gray-400
  `;

  sizes = {
    sm: 'px-3 py-2 text-sm rounded-lg',
    md: 'px-4 py-3 text-base rounded-xl',
    lg: 'px-5 py-4 text-lg rounded-xl'
  };

  variants = {
    default: `
      border-2 border-gray-300 focus:border-blue-600 focus:ring-4 focus:ring-blue-100
      bg-white hover:border-gray-400 disabled:bg-gray-50
    `,
    filled: `
      border-2 border-transparent bg-gray-100 focus:bg-white focus:border-blue-600
      focus:ring-4 focus:ring-blue-100 hover:bg-gray-50 disabled:bg-gray-200
    `,
    underlined: `
      border-0 border-b-2 border-gray-300 focus:border-blue-600 bg-transparent
      rounded-none hover:border-gray-400 focus:ring-0
    `
  };

  labelClasses:Signal<string> = computed<string>(()=>{
    return `
    block text-sm font-medium mb-2 transition-colors duration-200
    ${this.error() ? 'text-red-700' : this.focused() ? 'text-blue-700' : 'text-gray-700'}
    ${this.required() ? "after:content-['*'] after:ml-1 after:text-red-500" : ''}
  `});

  inputClasses = computed(()=>{
    return `
    ${this.baseClasses}
    ${this.variants[this.variant()]}
    ${this.sizes[this.size()]}
    ${this.leftIcon() ? (this.size() === 'sm' ? 'pl-10' : this.size() === 'lg' ? 'pl-14' : 'pl-12') : ''}
    ${this.rightIcon() || (this.inputType() === 'password' && this.showPasswordToggle()) ? (this.size() === 'sm' ? 'pr-10' : this.size() === 'lg' ? 'pr-14' : 'pr-12') : ''}
    ${this.error() ? 'border-red-500 focus:border-red-500 focus:ring-red-100' : ''}
    ${!this.fullWidth() ? 'w-auto' : ''}
    ${this.variant() === 'underlined' ? 'px-0' : ''}
    ${this.classes()}
  `});

  isFocused:WritableSignal<boolean> = signal<boolean>(false);

  handleFocus = (e:FocusEvent) => {
    this.isFocused.set(true);
    this.onFocus.emit((e.target as HTMLInputElement).value);
  };

  handleBlur = (e:FocusEvent) => {
    this.isFocused.set(false);
    this.onBlur.emit((e.target as HTMLInputElement).value);
  };

  inputName = this.label() ? this.label()!.replace(" ", "-").toLocaleLowerCase(): this.name();

  showPassword:WritableSignal<boolean> = signal<boolean>(false);

  togglePasswordVisibility(){
    this.showPassword.update(pre=>!pre)
  }

  protected readonly type:Signal<InputType> = computed(()=>{
    if(this.inputType()=='password'){
        return this.showPassword()? 'text' : 'password';
    }
    return this.inputType();
  })




}


export type InputType = 'text' | 'password' | 'email' | 'number' | 'disabled';

export type InputVariant = 'filled' | 'underlined' | 'default';
export type InputSize = 'sm' | 'md' | 'lg';
