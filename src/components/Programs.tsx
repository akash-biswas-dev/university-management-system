import { ArrowRight, Award, BookOpen, Globe, Users } from "lucide-react";

const Programs = () => {


  const programs = [
    {
      name: "Computer Science & Engineering",
      description:
        "Cutting-edge curriculum in AI, ML, and software development",
      students: "2,500+",
      icon: <BookOpen className="h-8 w-8" />,
    },
    {
      name: "Business Administration",
      description:
        "Comprehensive business education with real-world applications",
      students: "1,800+",
      icon: <Users className="h-8 w-8" />,
    },
    {
      name: "Engineering & Technology",
      description: "Advanced engineering programs across multiple disciplines",
      students: "3,200+",
      icon: <Award className="h-8 w-8" />,
    },
    {
      name: "Liberal Arts & Sciences",
      description:
        "Diverse programs fostering critical thinking and creativity",
      students: "1,200+",
      icon: <Globe className="h-8 w-8" />,
    },
  ];

  return (
    <section className="py-20 bg-white">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl lg:text-5xl font-bold text-primary mb-6">
            Academic Programs
          </h2>
          <p className="text-xl text-gray-600">
            Discover our comprehensive range of undergraduate and graduate
            programs
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8">
          {programs.map((program, index) => (
            <div
              key={index}
              className="group bg-gradient-to-br from-blue-50 to-blue-100 rounded-xl p-6 hover:from-blue-100 hover:to-blue-200 transition-all duration-300 transform hover:-translate-y-2 hover:shadow-xl"
            >
              <div className="text-secondary mb-4 group-hover:text-primary transition-colors duration-300">
                {program.icon}
              </div>
              <h3 className="text-xl font-bold text-primary mb-3">
                {program.name}
              </h3>
              <p className="text-gray-700 mb-4">{program.description}</p>
              <div className="flex items-center justify-between">
                <span className="text-sm font-semibold text-secondary">
                  {program.students} students
                </span>
                <button className="text-secondary hover:text-primary transition-colors duration-200">
                  <ArrowRight className="h-5 w-5" />
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
};

export default Programs;


export const Clock = ({ className }: { className?: string }) => (
  <svg
    className={className}
    fill="none"
    stroke="currentColor"
    viewBox="0 0 24 24"
  >
    <circle cx="12" cy="12" r="10"></circle>
    <polyline points="12,6 12,12 16,14"></polyline>
  </svg>
);