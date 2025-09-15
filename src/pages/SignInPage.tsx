import { AlertCircle, ArrowRight, Lock, Mail } from "lucide-react";
import { useState, type FormEvent } from "react";
import { Button, Input } from "../components";

type FormErrors = {
  email?: string;
  password?: string;
  general?: string;
};


const SignInPage = () => {

  

  const [formData, setFormData] = useState<{ email: string| undefined; password: string |undefined }>({
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState<FormErrors>({});
  const [loading, setLoading] = useState(false);
  const [rememberMe, setRememberMe] = useState(false);

  const handleEmailChange =(value:string)=>{

  }

  const handlePasswordChange =(value:string)=>{

  }

  
  const validateForm = () => {
    const newErrors: FormErrors = {};

    // Email validation
    if (!formData.email) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "Please enter a valid email address";
    }

    // Password validation
    if (!formData.password) {
      newErrors.password = "Password is required";
    } else if (formData.password.length < 6) {
      newErrors.password = "Password must be at least 6 characters";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    setLoading(true);

    // Simulate API call
    try {
      await new Promise((resolve) => setTimeout(resolve, 2000));

      // Simulate successful login
      alert("Login successful! Welcome to GlobalTech University.");

      // Reset form
      setFormData({ email: "", password: "" });
    } catch (error) {
      setErrors({ general: "Login failed. Please try again." });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
      
      {/* Background Pattern */}
      <div className="absolute inset-0 overflow-hidden">
        <div className="absolute top-0 right-0 w-96 h-96 bg-blue-100 rounded-full opacity-20 translate-x-1/2 -translate-y-1/2"></div>
        <div className="absolute bottom-0 left-0 w-80 h-80 bg-yellow-100 rounded-full opacity-30 -translate-x-1/2 translate-y-1/2"></div>
        <div className="absolute top-1/2 left-1/4 w-64 h-64 bg-blue-50 rounded-full opacity-40"></div>
      </div>

      <div className="relative max-w-md w-full">
        {/* Header */}
        <div className="text-center mb-8">
          <div className="mx-auto w-16 h-16 bg-blue-800 rounded-2xl flex items-center justify-center mb-6">
            <div className="text-white text-2xl font-bold">GT</div>
          </div>
          <h1 className="text-3xl font-bold text-gray-900 mb-2">
            Welcome Back
          </h1>
          <p className="text-gray-600">
            Sign in to your GlobalTech University account
          </p>
        </div>

        {/* SignIn Form */}
        <form className="bg-white rounded-2xl shadow-xl p-8" onSubmit={handleSubmit}>
          <div className="space-y-6">
            {/* General Error */}
            {errors.general && (
              <div className="bg-red-50 border border-red-200 rounded-xl p-4 flex items-center">
                <AlertCircle className="w-5 h-5 text-red-500 mr-3 flex-shrink-0" />
                <span className="text-red-700">{errors.general}</span>
              </div>
            )}

            {/* Email Field */}
            <Input
              label="Email Address"
              type="email"
              placeholder="student@globaltech.edu"
              leftIcon={<Mail />}
              value={formData.email}
              onChange={handleEmailChange}
              error={errors.email}
              required
              size="lg"
            />

            {/* Password Field */}
            <Input
              label="Password"
              type="password"
              placeholder="Enter your password"
              leftIcon={<Lock />}
              showPasswordToggle
              value={formData.password}
              onChange={handlePasswordChange}
              error={errors.password}
              required
              size="lg"
            />

            {/* Remember Me & Forgot Password */}
            <div className="flex items-center justify-between">
              <label className="flex items-center">
                <input
                  type="checkbox"
                  className="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2"
                  checked={rememberMe}
                  onChange={(e) => setRememberMe(e.target.checked)}
                />
                <span className="ml-2 text-sm text-gray-700">Remember me</span>
              </label>
              <button className="text-sm text-blue-600 hover:text-blue-800 font-medium transition-colors duration-200">
                Forgot password?
              </button>
            </div>

            {/* Sign In Button */}
            <Button
              variant="primary"
              size="lg"
              fullWidth
              loading={loading}
              icon={!loading && <ArrowRight className="w-5 h-5" />}
              iconPosition="right"
            >
              {loading ? "Signing In..." : "Sign In"}
            </Button>

            {/* Divider */}
            <div className="relative">
              <div className="absolute inset-0 flex items-center">
                <div className="w-full border-t border-gray-300"></div>
              </div>
              <div className="relative flex justify-center text-sm">
                <span className="px-2 bg-white text-gray-500">
                  Or continue with
                </span>
              </div>
            </div>

            {/* Social Login Options */}
            <div className="grid grid-cols-2 gap-4">
              <Button variant="outline" size="md" fullWidth>
                <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24">
                  <path
                    fill="currentColor"
                    d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
                  />
                  <path
                    fill="currentColor"
                    d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
                  />
                  <path
                    fill="currentColor"
                    d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
                  />
                  <path
                    fill="currentColor"
                    d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
                  />
                </svg>
                Google
              </Button>

              <Button variant="outline" size="md" fullWidth>
                <svg
                  className="w-5 h-5 mr-2"
                  fill="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path d="M13.397 20.997v-8.196h2.765l.411-3.209h-3.176V7.548c0-.926.258-1.56 1.587-1.56h1.684V3.127A22.336 22.336 0 0 0 14.201 3c-2.444 0-4.122 1.492-4.122 4.231v2.355H7.332v3.209h2.753v8.202h3.312z" />
                </svg>
                Facebook
              </Button>
            </div>
          </div>

          {/* Sign Up Link */}
          <div className="mt-8 text-center">
            <p className="text-sm text-gray-600">
              Don't have an account?{" "}
              <Button variant="outline" href="/signup" buttonType="navigate" size="sm">
                Create an Account
              </Button>
            </p>
          </div>
        </form>

        {/* Footer */}
        <div className="mt-8 text-center">
          <p className="text-xs text-gray-500">
            © 2025 GlobalTech University. All rights reserved.
          </p>
        </div>
      </div>
    </div>
  );
};

export default SignInPage;
