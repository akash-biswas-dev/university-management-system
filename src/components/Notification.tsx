import { Bell, ChevronRight } from "lucide-react";

const Notification = () => {

//  Fetch this data from the server.
  const notifications = [
    {
      id: 1,
      title: "Fall 2025 Admission Open",
      description:
        "Applications for undergraduate and graduate programs are now open",
      date: "Sep 10, 2025",
      type: "admission",
    },
    {
      id: 2,
      title: "Research Conference 2025",
      description:
        "Annual research conference featuring latest innovations in technology",
      date: "Oct 15, 2025",
      type: "event",
    },
    {
      id: 3,
      title: "Scholarship Program Launch",
      description:
        "New merit-based scholarship program for outstanding students",
      date: "Sep 20, 2025",
      type: "scholarship",
    },
  ];


  return (
    <section className="py-20 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl lg:text-5xl font-bold text-primary mb-6">
            Latest Announcements
          </h2>
          <p className="text-xl text-gray-600">
            Stay updated with important news and announcements
          </p>
        </div>

        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-8">
          {notifications.map((notification) => (
            <div
              key={notification.id}
              className="bg-white rounded-xl shadow-lg p-6 hover:shadow-xl transition-all duration-300 transform hover:-translate-y-1"
            >
              <div className="flex items-center mb-4">
                <Bell className="h-6 w-6 text-tertiary mr-3" />
                <span
                  className={`px-3 py-1 rounded-full text-sm font-medium ${
                    notification.type === "admission"
                      ? "bg-green-100 text-green-800"
                      : notification.type === "event"
                      ? "bg-blue-100 text-blue-800"
                      : "bg-yellow-100 text-yellow-800"
                  }`}
                >
                  {notification.type.charAt(0).toUpperCase() +
                    notification.type.slice(1)}
                </span>
              </div>
              <h3 className="text-xl font-bold text-primary mb-3">
                {notification.title}
              </h3>
              <p className="text-gray-600 mb-4">{notification.description}</p>
              <div className="flex items-center justify-between">
                <span className="text-sm text-gray-500">
                  {notification.date}
                </span>
                <button className="text-secondary hover:text-primary transition-colors duration-200">
                  <ChevronRight className="h-5 w-5" />
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}

export default Notification