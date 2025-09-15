import { GraduationCap, Menu, X } from "lucide-react";
import { useEffect, useState } from "react";

const Navbar = () => {
  const [isScrolled, setIsScrolled] = useState(false);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setIsScrolled(window.scrollY > 20);
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  return (
    <nav
      className={`fixed top-0 left-0 right-0 z-50 transition-all duration-300 bg-transparent`}
    >
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16 lg:h-20">
          {/* Left Side - University Name & Logo */}
          <div className="flex items-center space-x-3">
            <div
              className={`w-10 h-10 rounded-xl flex items-center justify-center transition-all duration-300 ${
                isScrolled ? "bg-blue-800" : "bg-white/20 backdrop-blur-sm"
              }`}
            >
              <GraduationCap
                className={`w-6 h-6 transition-colors duration-300 ${
                  isScrolled ? "text-white" : "text-white"
                }`}
              />
            </div>

            <div className="flex flex-col">
              <h1
                className={`text-xl lg:text-2xl font-bold transition-colors duration-300 ${
                  isScrolled ? "text-gray-900" : "text-white"
                }`}
              >
                GlobalTech University
              </h1>
              <span
                className={`text-xs font-medium transition-colors duration-300 ${
                  isScrolled ? "text-blue-600" : "text-white/80"
                }`}
              >
                Empowering Tomorrow
              </span>
            </div>
          </div>

          {/* Right Side - Desktop Buttons */}
          <div className="hidden md:flex items-center space-x-4">
            {/* Sign Up Button */}
            <button
              className={`px-6 py-2.5 text-sm font-semibold rounded-xl transition-all duration-300 transform hover:scale-105 ${
                isScrolled
                  ? "text-blue-800 hover:bg-blue-50 border-2 border-blue-800 bg-transparent hover:bg-blue-800 hover:text-white"
                  : "text-white hover:bg-white/20 border-2 border-white/50 bg-transparent hover:bg-white hover:text-blue-900"
              }`}
            >
              Sign Up
            </button>

            {/* Login Button */}
            <button
              className={`px-6 py-2.5 text-sm font-semibold rounded-xl transition-all duration-300 transform hover:scale-105 shadow-lg hover:shadow-xl ${
                isScrolled
                  ? "bg-blue-800 hover:bg-blue-900 text-white"
                  : "bg-white/20 backdrop-blur-sm hover:bg-white text-white hover:text-blue-900 border border-white/30"
              }`}
            >
              Login
            </button>
          </div>

          {/* Mobile Menu Button */}
          <div className="md:hidden">
            <button
              onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
              className={`p-2 rounded-lg transition-colors duration-200 ${
                isScrolled
                  ? "text-gray-900 hover:bg-gray-100"
                  : "text-white hover:bg-white/20"
              }`}
            >
              {isMobileMenuOpen ? (
                <X className="w-6 h-6" />
              ) : (
                <Menu className="w-6 h-6" />
              )}
            </button>
          </div>
        </div>
      </div>

      {/* Mobile Menu */}
      <div
        className={`md:hidden transition-all duration-300 ease-in-out ${
          isMobileMenuOpen
            ? "max-h-48 opacity-100"
            : "max-h-0 opacity-0 overflow-hidden"
        }`}
      >
        <div className="bg-white/95 backdrop-blur-md border-t border-gray-200/20 shadow-lg">
          <div className="max-w-7xl mx-auto px-4 py-4 space-y-3">
            {/* Mobile Sign Up Button */}
            <button className="w-full px-4 py-3 text-blue-800 font-semibold rounded-xl border-2 border-blue-800 bg-transparent hover:bg-blue-800 hover:text-white transition-all duration-300">
              Sign Up
            </button>

            {/* Mobile Login Button */}
            <button className="w-full px-4 py-3 bg-blue-800 hover:bg-blue-900 text-white font-semibold rounded-xl transition-all duration-300 shadow-lg">
              Login
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
};


export default Navbar;