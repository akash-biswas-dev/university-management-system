import { Loader2 } from "lucide-react";
import type { ReactNode } from "react";
import { Link } from "react-router-dom";

const Button = ({
  children,
  variant,
  size = "md",
  buttonType = "button",
  href = "/",
  fullWidth,
  loading,
  disabled,
  className,
  icon,
  iconPosition = "left",
  onClick
}: {
  children: ReactNode;
  variant: ButtonVarient;
  size?: ButtonSize;
  buttonType?: ButtonType;
  href?: string;
  fullWidth?: boolean;
  loading?: boolean;
  disabled?: boolean;
  className?: string;
  icon?: ReactNode;
  iconPosition?: "left" | "right";
  onClick?: () => void;
}) => {
  const baseClasses = `
    inline-flex items-center justify-center font-semibold transition-all duration-300 
    focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed
    transform hover:scale-105 active:scale-95 cursor-pointer
  `;

  const variants = {
    primary:
      "bg-blue-800 hover:bg-blue-900 text-white focus:ring-blue-500 shadow-lg hover:shadow-xl",
    secondary:
      "bg-blue-600 hover:bg-blue-700 text-white focus:ring-blue-400 shadow-lg hover:shadow-xl",
    tertiary:
      "bg-yellow-500 hover:bg-yellow-600 text-gray-900 focus:ring-yellow-400 shadow-lg hover:shadow-xl",
    outline:
      "border-2 border-blue-800 text-blue-800 hover:bg-blue-800 hover:text-white focus:ring-blue-500 bg-white",
    ghost: "text-blue-800 hover:bg-blue-50 focus:ring-blue-500 bg-transparent",
    danger:
      "bg-red-600 hover:bg-red-700 text-white focus:ring-red-500 shadow-lg hover:shadow-xl",
    success:
      "bg-green-600 hover:bg-green-700 text-white focus:ring-green-500 shadow-lg hover:shadow-xl",
  };

  const sizes = {
    sm: "px-4 py-2 text-sm rounded-lg",
    md: "px-6 py-3 text-base rounded-xl",
    lg: "px-8 py-4 text-lg rounded-xl",
    xl: "px-10 py-5 text-xl rounded-2xl",
  };

  const classes = `
    ${baseClasses}
    ${variants[variant]}
    ${sizes[size]}
    ${fullWidth ? "w-full" : ""}
    ${disabled || loading ? "pointer-events-none" : ""}
    ${className}
  `;

  if (buttonType === "navigate") {
    return (
      <Link className={classes} to={href}>
        {children}
      </Link>
    );
  }

  return (
    <button
      className={classes}
      type={buttonType}
      disabled={disabled}
      onClick={onClick && onClick}
    >
      {loading && <Loader2 className="w-4 h-4 mr-2 animate-spin" />}

      {icon && iconPosition === "left" && !loading && (
        <span className="mr-2">{icon}</span>
      )}

      {children}

      {icon && iconPosition === "right" && !loading && (
        <span className="ml-2">{icon}</span>
      )}
    </button>
  );
};

export default Button;

export type ButtonVarient =
  | "primary"
  | "secondary"
  | "tertiary"
  | "outline"
  | "ghost"
  | "danger"
  | "success";

export type ButtonSize = "sm" | "md" | "lg" | "xl";

export type ButtonType = "button" | "submit" | "reset" | "navigate";
