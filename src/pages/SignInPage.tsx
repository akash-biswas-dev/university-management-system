import { AlertCircle, ArrowRight, Lock, Mail } from "lucide-react";
import { useState, type FormEvent } from "react";
import { Button, Input } from "../components";
import { Link } from "react-router-dom";
import useAuthContext from "../context/AuthContext";

type FormErrors = {
  email?: string;
  password?: string;
  general?: string;
};

const SignInPage = () => {

  const {login} = useAuthContext();

  const [formData, setFormData] = useState<{
    username: string | undefined;
    password: string | undefined;
  }>({
    username: "",
    password: "",
  });

  const [errors, setErrors] = useState<FormErrors>({});
  const [loading, setLoading] = useState(false);
  const [rememberMe, setRememberMe] = useState(false);

  const handleEmailChange = (value: string) => {
    setFormData((pre) => ({ ...pre, username: value }));
  };

  const handlePasswordChange = (value: string) => {
    setFormData((pre) => ({ ...pre, password: value }));
  };

  const validateForm = () => {
    const newErrors: FormErrors = {};

    // Email validation
    if (!formData.username) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(formData.username)) {
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

    await login({
      username: formData.username!,
      password: formData.password!
    });
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
            <Link to={"/home"} className="text-white text-2xl font-bold">GT</Link>
          </div>
          <h1 className="text-3xl font-bold text-gray-900 mb-2">
            Welcome Back
          </h1>
          <p className="text-gray-600">
            Sign in to your GlobalTech University account
          </p>
        </div>

        {/* SignIn Form */}
        <form
          className="bg-white rounded-2xl shadow-xl p-8"
          onSubmit={handleSubmit}
        >
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
              value={formData.username}
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
              showPasswordToggle={true}
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
          </div>

          {/* Sign Up Link */}
          <div className="mt-8 text-center flex gap-2 items-center justify-center">
            <p className="text-sm text-gray-600">Don't have an account? </p>
            <Button
              variant="ghost"
              href="/auth/sign-up"
              buttonType="navigate"
              size="sm"
            >
              Create an Account
            </Button>
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
