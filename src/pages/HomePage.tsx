import {
  ArrowRight,
  Award,
  BookOpen,
  Globe,
  Mail,
  MapPin,
  Phone,
  Users
} from "lucide-react";
import Events from "../components/Events";
import Notification from "../components/Notification";
import Programs from "../components/Programs";

const HomePage = () => {


  return (
    <div className="min-h-screen bg-gray-50">

      {/* Hero Section */}
      <Hero/>
      
      {/* About Section */}
      <About/>

      {/* Notifications Section */}
      <Notification/>  

      {/* Programs Section */}
      <Programs/>

      {/* Events Section */}
      <Events/>

      {/* Footer */}
      <footer className="bg-primary text-white py-16">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-12">
            <div>
              <h3 className="text-2xl font-bold mb-6">GlobalTech University</h3>
              <p className="text-blue-200 mb-6 leading-relaxed">
                Empowering minds and transforming futures through world-class
                education and innovative research.
              </p>
              <div className="flex space-x-4">
                <div className="w-10 h-10 bg-secondary rounded-full flex items-center justify-center hover:bg-tertiary transition-colors duration-300 cursor-pointer">
                  <Globe className="h-5 w-5" />
                </div>
                <div className="w-10 h-10 bg-secondary rounded-full flex items-center justify-center hover:bg-tertiary transition-colors duration-300 cursor-pointer">
                  <Mail className="h-5 w-5" />
                </div>
              </div>
            </div>

            <div>
              <h4 className="text-lg font-semibold mb-6">Quick Links</h4>
              <ul className="space-y-3">
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    About Us
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Academics
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Admissions
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Research
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Campus Life
                  </a>
                </li>
              </ul>
            </div>

            <div>
              <h4 className="text-lg font-semibold mb-6">Student Services</h4>
              <ul className="space-y-3">
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Library
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Career Services
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Financial Aid
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Student Portal
                  </a>
                </li>
                <li>
                  <a
                    href="#"
                    className="text-blue-200 hover:text-white transition-colors duration-200"
                  >
                    Housing
                  </a>
                </li>
              </ul>
            </div>

            <div>
              <h4 className="text-lg font-semibold mb-6">Contact Info</h4>
              <div className="space-y-4">
                <div className="flex items-start">
                  <MapPin className="h-5 w-5 text-tertiary mr-3 mt-1 flex-shrink-0" />
                  <div className="text-blue-200">
                    <p>123 University Avenue</p>
                    <p>Tech City, TC 12345</p>
                  </div>
                </div>
                <div className="flex items-center">
                  <Phone className="h-5 w-5 text-tertiary mr-3" />
                  <span className="text-blue-200">+1 (555) 123-4567</span>
                </div>
                <div className="flex items-center">
                  <Mail className="h-5 w-5 text-tertiary mr-3" />
                  <span className="text-blue-200">info@globaltech.edu</span>
                </div>
              </div>
            </div>
          </div>

          <div className="border-t border-blue-800 mt-12 pt-8">
            <div className="flex flex-col lg:flex-row justify-between items-center">
              <p className="text-blue-200 mb-4 lg:mb-0">
                © 2025 GlobalTech University. All rights reserved.
              </p>
              <div className="flex space-x-6">
                <a
                  href="#"
                  className="text-blue-200 hover:text-white transition-colors duration-200"
                >
                  Privacy Policy
                </a>
                <a
                  href="#"
                  className="text-blue-200 hover:text-white transition-colors duration-200"
                >
                  Terms of Service
                </a>
                <a
                  href="#"
                  className="text-blue-200 hover:text-white transition-colors duration-200"
                >
                  Accessibility
                </a>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
};

// Missing Clock import - adding it


export default HomePage;



export const Hero = () => {
  return (
    <section className="relative bg-white overflow-hidden">
      {/* Background Pattern */}
      <div className="absolute inset-0">
        <div className="absolute top-0 left-0 w-72 h-72 bg-blue-100 rounded-full opacity-20 -translate-x-1/2 -translate-y-1/2"></div>
        <div className="absolute top-20 right-0 w-96 h-96 bg-yellow-100 rounded-full opacity-30 translate-x-1/2 -translate-y-1/4"></div>
        <div className="absolute bottom-0 left-1/4 w-80 h-80 bg-blue-50 rounded-full opacity-40 translate-y-1/2"></div>
      </div>

      <div className="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16 lg:py-24">
        <div className="grid lg:grid-cols-2 gap-12 items-center">
          {/* Left Column - Content */}
          <div className="space-y-8">
            <div className="inline-flex items-center bg-blue-50 text-primary px-4 py-2 rounded-full text-sm font-medium">
              <Award className="h-4 w-4 mr-2" />
              Ranked #1 Technology University
            </div>

            <h1 className="text-5xl lg:text-6xl font-bold text-gray-900 leading-tight">
              Welcome to
              <span className="block text-primary">GlobalTech</span>
              <span className="block text-tertiary">University</span>
            </h1>

            <p className="text-xl text-gray-600 leading-relaxed max-w-lg">
              Shape your future with cutting-edge education, world-class
              research, and a vibrant campus community that prepares you for
              tomorrow's challenges.
            </p>

            <div className="flex flex-col sm:flex-row gap-4">
              <button className="group bg-primary hover:bg-blue-900 text-white px-8 py-4 rounded-xl text-lg font-semibold transition-all duration-300 shadow-lg hover:shadow-xl">
                Start Your Journey
                <ArrowRight className="inline ml-2 h-5 w-5 group-hover:translate-x-1 transition-transform" />
              </button>
              <button className="border-2 border-primary text-primary hover:bg-primary hover:text-white px-8 py-4 rounded-xl text-lg font-semibold transition-all duration-300">
                Explore Programs
              </button>
            </div>

            {/* Quick Stats */}
            <div className="grid grid-cols-3 gap-6 pt-8 border-t border-gray-200">
              <div className="text-center">
                <div className="text-2xl font-bold text-primary">50K+</div>
                <div className="text-sm text-gray-600">Students</div>
              </div>
              <div className="text-center">
                <div className="text-2xl font-bold text-primary">200+</div>
                <div className="text-sm text-gray-600">Programs</div>
              </div>
              <div className="text-center">
                <div className="text-2xl font-bold text-primary">95%</div>
                <div className="text-sm text-gray-600">Job Rate</div>
              </div>
            </div>
          </div>

          {/* Right Column - Visual Elements */}
          <div className="relative">
            <div className="bg-gradient-to-br from-primary to-secondary rounded-3xl p-8 text-white transform rotate-3 hover:rotate-0 transition-transform duration-500">
              <div className="space-y-6">
                <div className="flex items-center space-x-3">
                  <div className="w-12 h-12 bg-white bg-opacity-20 rounded-xl flex items-center justify-center">
                    <BookOpen className="h-6 w-6" />
                  </div>
                  <div>
                    <div className="font-semibold">World-Class Education</div>
                    <div className="text-sm opacity-80">
                      Learn from industry experts
                    </div>
                  </div>
                </div>

                <div className="flex items-center space-x-3">
                  <div className="w-12 h-12 bg-white bg-opacity-20 rounded-xl flex items-center justify-center">
                    <Users className="h-6 w-6" />
                  </div>
                  <div>
                    <div className="font-semibold">Global Community</div>
                    <div className="text-sm opacity-80">
                      Connect with peers worldwide
                    </div>
                  </div>
                </div>

                <div className="flex items-center space-x-3">
                  <div className="w-12 h-12 bg-white bg-opacity-20 rounded-xl flex items-center justify-center">
                    <Globe className="h-6 w-6" />
                  </div>
                  <div>
                    <div className="font-semibold">Career Success</div>
                    <div className="text-sm opacity-80">
                      95% employment rate
                    </div>
                  </div>
                </div>
              </div>

              <div className="mt-8 p-4 bg-white bg-opacity-10 rounded-xl backdrop-blur">
                <div className="text-sm opacity-90 mb-2">
                  Next Application Deadline
                </div>
                <div className="text-2xl font-bold">December 15, 2025</div>
              </div>
            </div>

            {/* Floating Elements */}
            <div className="absolute -top-4 -right-4 w-20 h-20 bg-tertiary rounded-2xl flex items-center justify-center text-white font-bold text-xl shadow-lg animate-bounce">
              #1
            </div>

            <div className="absolute -bottom-6 -left-6 w-16 h-16 bg-white rounded-full shadow-lg flex items-center justify-center border-4 border-blue-100">
              <Award className="h-6 w-6 text-tertiary" />
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}


export const About = () => {
  return (
    <section className="py-20 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl lg:text-5xl font-bold text-primary mb-6">
            About GlobalTech University
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Founded on the principles of innovation, excellence, and inclusivity
          </p>
        </div>

        <div className="grid lg:grid-cols-2 gap-16 items-center">
          <div>
            <h3 className="text-2xl font-bold text-primary mb-6">
              Our Mission
            </h3>
            <p className="text-lg text-gray-700 mb-8 leading-relaxed">
              At GlobalTech University, we are committed to fostering an
              environment where knowledge meets innovation. Our mission is to
              prepare students for the challenges of tomorrow by providing
              world-class education, cutting-edge research opportunities, and a
              vibrant campus community.
            </p>

            <div className="grid sm:grid-cols-2 gap-8">
              <div className="text-center">
                <div className="text-3xl font-bold text-secondary mb-2">
                  50,000+
                </div>
                <div className="text-gray-600">Students Worldwide</div>
              </div>
              <div className="text-center">
                <div className="text-3xl font-bold text-secondary mb-2">
                  200+
                </div>
                <div className="text-gray-600">Degree Programs</div>
              </div>
              <div className="text-center">
                <div className="text-3xl font-bold text-secondary mb-2">
                  95%
                </div>
                <div className="text-gray-600">Graduate Employment</div>
              </div>
              <div className="text-center">
                <div className="text-3xl font-bold text-secondary mb-2">
                  150+
                </div>
                <div className="text-gray-600">Countries Represented</div>
              </div>
            </div>
          </div>

          <div className="bg-gradient-to-br from-blue-50 to-blue-100 p-8 rounded-2xl">
            <h3 className="text-2xl font-bold text-primary mb-6">Our Values</h3>
            <div className="space-y-6">
              <div className="flex items-start space-x-4">
                <div className="bg-secondary w-10 h-10 rounded-full flex items-center justify-center flex-shrink-0">
                  <Award className="h-5 w-5 text-white" />
                </div>
                <div>
                  <h4 className="font-semibold text-primary mb-2">
                    Excellence in Education
                  </h4>
                  <p className="text-gray-700">
                    Delivering world-class education with innovative teaching
                    methods and comprehensive curricula.
                  </p>
                </div>
              </div>

              <div className="flex items-start space-x-4">
                <div className="bg-secondary w-10 h-10 rounded-full flex items-center justify-center flex-shrink-0">
                  <Users className="h-5 w-5 text-white" />
                </div>
                <div>
                  <h4 className="font-semibold text-primary mb-2">
                    Inclusive Community
                  </h4>
                  <p className="text-gray-700">
                    Building a diverse, welcoming environment where every
                    student can thrive and succeed.
                  </p>
                </div>
              </div>

              <div className="flex items-start space-x-4">
                <div className="bg-secondary w-10 h-10 rounded-full flex items-center justify-center flex-shrink-0">
                  <Globe className="h-5 w-5 text-white" />
                </div>
                <div>
                  <h4 className="font-semibold text-primary mb-2">
                    Global Impact
                  </h4>
                  <p className="text-gray-700">
                    Preparing graduates to make meaningful contributions to
                    society and drive positive change.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};