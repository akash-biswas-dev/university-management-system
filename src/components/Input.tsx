import  { useState } from 'react';
import { Eye, EyeOff } from 'lucide-react';
import type { FocusEvent, ReactNode } from 'react';

// Input Component
const Input = ({ 
  label,
  error,
  helper,
  type = 'text',
  size = 'md',
  variant = 'default',
  leftIcon,
  rightIcon,
  showPasswordToggle = false,
  fullWidth = true,
  placeholder,
  value,
  onChange,
  onFocus,
  onBlur,
  disabled = false,
  required = false,
  className = ''
}:{
  label:string;
  error?:string;
  helper?:string;
  type?:string;
  size?:InputSize;
  variant?:InputVarient;
  leftIcon?:ReactNode;
  rightIcon?:ReactNode;
  showPasswordToggle?:boolean;
  fullWidth?:boolean;
  placeholder?:string;
  value?:string;
  onChange?:(value:string)=>void;
  onFocus?:any;
  onBlur?:any;
  disabled?:boolean;
  required?:boolean;
  className?:string;
}) => {
  
  const [showPassword, setShowPassword] = useState(false);
  const [focused, setFocused] = useState(false);
  
  const inputType = type === 'password' && showPassword ? 'text' : type;
  
  const baseClasses = `
    w-full transition-all duration-300 focus:outline-none 
    disabled:opacity-50 disabled:cursor-not-allowed
    placeholder:text-gray-400
  `;
  
  const variants = {
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
  
  const sizes = {
    sm: 'px-3 py-2 text-sm rounded-lg',
    md: 'px-4 py-3 text-base rounded-xl',
    lg: 'px-5 py-4 text-lg rounded-xl'
  };
  
  const inputClasses = `
    ${baseClasses}
    ${variants[variant]}
    ${sizes[size]}
    ${leftIcon ? (size === 'sm' ? 'pl-10' : size === 'lg' ? 'pl-14' : 'pl-12') : ''}
    ${rightIcon || (type === 'password' && showPasswordToggle) ? (size === 'sm' ? 'pr-10' : size === 'lg' ? 'pr-14' : 'pr-12') : ''}
    ${error ? 'border-red-500 focus:border-red-500 focus:ring-red-100' : ''}
    ${!fullWidth ? 'w-auto' : ''}
    ${variant === 'underlined' ? 'px-0' : ''}
    ${className}
  `;
  
  const labelClasses = `
    block text-sm font-medium mb-2 transition-colors duration-200
    ${error ? 'text-red-700' : focused ? 'text-blue-700' : 'text-gray-700'}
    ${required ? "after:content-['*'] after:ml-1 after:text-red-500" : ''}
  `;

  const handleFocus = (e:FocusEvent<HTMLInputElement,Element>) => {
    setFocused(true);
    if (onFocus) onFocus(e.target.value);
  };

  const handleBlur = (e:FocusEvent<HTMLInputElement,Element>) => {
    setFocused(false);
    if (onBlur) onBlur(e.target.value);
  };

  const getIconSize = () => {
    switch (size) {
      case 'sm': return 'w-4 h-4';
      case 'lg': return 'w-6 h-6';
      default: return 'w-5 h-5';
    }
  };

  const getIconPosition = () => {
    switch (size) {
      case 'sm': return variant === 'underlined' ? 'left-0 top-2' : 'left-3 top-2';
      case 'lg': return variant === 'underlined' ? 'left-0 top-4' : 'left-4 top-4';
      default: return variant === 'underlined' ? 'left-0 top-3' : 'left-4 top-3';
    }
  };

  const getRightIconPosition = () => {
    switch (size) {
      case 'sm': return variant === 'underlined' ? 'right-0 top-2' : 'right-3 top-2';
      case 'lg': return variant === 'underlined' ? 'right-0 top-4' : 'right-4 top-4';
      default: return variant === 'underlined' ? 'right-0 top-3' : 'right-4 top-3';
    }
  };

  return (
    <div className={`${fullWidth ? 'w-full' : 'w-auto'}`}>
      {label && (
        <label className={labelClasses}>
          {label}
        </label>
      )}
      
      <div className="relative">
        {leftIcon && (
          <div className={`absolute ${getIconPosition()} text-gray-400 pointer-events-none`}>
            <div className={getIconSize()}>
              {leftIcon}
            </div>
          </div>
        )}
        
        <input
          type={inputType}
          className={inputClasses}
          placeholder={placeholder}
          value={value}
          onChange={(e)=>onChange && onChange(e.target.value)}
          onFocus={handleFocus}
          onBlur={handleBlur}
          disabled={disabled}
          required={required}
        />
        
        {(rightIcon || (type === 'password' && showPasswordToggle)) && (
          <div className={`absolute ${getRightIconPosition()}`}>
            {type === 'password' && showPasswordToggle ? (
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="text-gray-400 hover:text-gray-600 transition-colors duration-200 focus:outline-none focus:text-blue-600"
                disabled={disabled}
              >
                <div className={getIconSize()}>
                  {showPassword ? <EyeOff /> : <Eye />}
                </div>
              </button>
            ) : (
              <div className={`text-gray-400 pointer-events-none ${getIconSize()}`}>
                {rightIcon}
              </div>
            )}
          </div>
        )}
      </div>
      
      {error && (
        <p className="mt-2 text-sm text-red-600 flex items-center">
          <svg className="w-4 h-4 mr-1 flex-shrink-0" fill="currentColor" viewBox="0 0 20 20">
            <path fillRule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7 4a1 1 0 11-2 0 1 1 0 012 0zm-1-9a1 1 0 00-1 1v4a1 1 0 102 0V6a1 1 0 00-1-1z" clipRule="evenodd" />
          </svg>
          {error}
        </p>
      )}
      
      {helper && !error && (
        <p className="mt-2 text-sm text-gray-500">{helper}</p>
      )}
    </div>
  );
};

export type InputType = 'text' | 'password' | 'email' | 'number';

export type InputVarient = 'filled' | 'underlined' | 'default';
export type InputSize = 'sm' | 'md' | 'lg';

export default Input;