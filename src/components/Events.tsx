import { Calendar, Clock, MapPin } from "lucide-react";

const Events = () => {



  const events = [
    {
      title: "Tech Innovation Summit",
      date: "October 22, 2025",
      time: "9:00 AM - 5:00 PM",
      location: "Main Auditorium",
      category: "Conference",
    },
    {
      title: "Career Fair 2025",
      date: "November 5, 2025",
      time: "10:00 AM - 4:00 PM",
      location: "Sports Complex",
      category: "Career",
    },
    {
      title: "International Student Welcome",
      date: "September 25, 2025",
      time: "2:00 PM - 6:00 PM",
      location: "Student Center",
      category: "Social",
    },
  ];

  return (
    <section className="py-20 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl lg:text-5xl font-bold text-primary mb-6">
            Upcoming Events
          </h2>
          <p className="text-xl text-gray-600">
            Join us for exciting events, seminars, and activities
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {events.map((event, index) => (
            <div
              key={index}
              className="bg-white rounded-xl shadow-lg p-6 hover:shadow-xl transition-all duration-300 transform hover:-translate-y-1"
            >
              <div className="flex items-center justify-between mb-4">
                <span
                  className={`px-3 py-1 rounded-full text-sm font-medium ${
                    event.category === "Conference"
                      ? "bg-blue-100 text-blue-800"
                      : event.category === "Career"
                      ? "bg-green-100 text-green-800"
                      : "bg-purple-100 text-purple-800"
                  }`}
                >
                  {event.category}
                </span>
                <Calendar className="h-5 w-5 text-secondary" />
              </div>
              <h3 className="text-xl font-bold text-primary mb-3">
                {event.title}
              </h3>
              <div className="space-y-2 text-gray-600">
                <div className="flex items-center">
                  <Calendar className="h-4 w-4 mr-2" />
                  <span>{event.date}</span>
                </div>
                <div className="flex items-center">
                  <Clock className="h-4 w-4 mr-2" />
                  <span>{event.time}</span>
                </div>
                <div className="flex items-center">
                  <MapPin className="h-4 w-4 mr-2" />
                  <span>{event.location}</span>
                </div>
              </div>
              <button className="mt-4 w-full bg-secondary hover:bg-primary text-white py-2 px-4 rounded-lg transition-colors duration-300">
                Learn More
              </button>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

export default Events