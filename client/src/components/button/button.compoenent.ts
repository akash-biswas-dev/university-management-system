import { Component, computed, input, InputSignal, output, OutputEmitterRef, Signal } from '@angular/core';
import { LucideAngularModule, ArrowRight, LucideIconData, LoaderCircle } from 'lucide-angular';
import { RouterLink } from '@angular/router';



@Component({
  selector: 'app-button',
  templateUrl: 'button.component.html',
  standalone: true,
  imports: [LucideAngularModule, RouterLink]
})
export class Button {

  readonly LoaderCircle = LoaderCircle;


  // Input variables.
  buttonName: InputSignal<string> = input.required<string>();
  variant: InputSignal<ButtonVariant> = input<ButtonVariant>('primary');
  size: InputSignal<ButtonSize> = input<ButtonSize>('md');
  buttonType: InputSignal<ButtonType> = input<ButtonType>('submit');
  href: InputSignal<string> = input<string>('/');
  fullWidth: InputSignal<boolean> = input<boolean>(false);
  loading: InputSignal<boolean> = input<boolean>(false);
  disabled: InputSignal<boolean> = input<boolean>(false);
  className: InputSignal<string> = input<string>('');
  icon: InputSignal<LucideIconData | undefined> = input<LucideIconData | undefined>(undefined);
  iconPosition: InputSignal<IconPosition> = input<IconPosition>('right');

  // Click event emitter.
  click: OutputEmitterRef<void> = output<void>()

  onClick(): void {
    this.click.emit();
  }

  private baseClasses = `
    inline-flex items-center justify-center font-semibold transition-all duration-300
    focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed
    transform hover:scale-105 active:scale-95 cursor-pointer
  `;

  private variants = {
    primary:
      "bg-primary hover:bg-slate-800 text-white focus:ring-primary shadow-lg hover:shadow-xl",
    secondary:
      "bg-secondary hover:bg-blue-700 text-white focus:ring-secondary shadow-lg hover:shadow-xl",
    tertiary:
      "bg-tertiary hover:bg-amber-600 text-white focus:ring-tertiary shadow-lg hover:shadow-xl",
    outline:
      "border-2 border-primary text-primary hover:bg-primary hover:text-white focus:ring-primary bg-white",
    ghost: "text-primary hover:bg-slate-100 focus:ring-primary bg-transparent",
    danger:
      "bg-red-600 hover:bg-red-700 text-white focus:ring-red-500 shadow-lg hover:shadow-xl",
    success:
      "bg-green-600 hover:bg-green-700 text-white focus:ring-green-500 shadow-lg hover:shadow-xl",
  }

  private sizes = {
    sm: "px-4 py-2 text-sm rounded-lg",
    md: "px-6 py-3 text-base rounded-xl",
    lg: "px-8 py-4 text-lg rounded-xl",
    xl: "px-10 py-5 text-xl rounded-2xl",
  };



  readonly classes: Signal<string> = computed<string>(() => {
    return `
    ${this.baseClasses}
    ${this.variants[this.variant()]}
    ${this.sizes[this.size()]}
    ${this.fullWidth() ? "w-full" : ""}
    ${this.disabled() || this.loading() ? "pointer-events-none" : ""}
    ${this.className()}
    `;
  })

}

export type ButtonVariant =
  | "primary"
  | "secondary"
  | "tertiary"
  | "outline"
  | "ghost"
  | "danger"
  | "success";
export type ButtonSize = "sm" | "md" | "lg" | "xl";
export type ButtonType = 'button' | 'submit' | 'reset' | 'navigate';
export type IconPosition = 'left' | 'right';
