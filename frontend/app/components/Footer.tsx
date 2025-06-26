import React from 'react'

const Footer = () => {
    return (
        <footer className="bg-gray-800 text-white text-sm py-4 mt-12">
            <div className="max-w-7xl mx-auto px-4 flex flex-col md:flex-row justify-between items-center">
                <p className="mb-2 md:mb-0">&copy; {new Date().getFullYear()} MyWebsite. All rights reserved.</p>

                <div className="space-x-4">
                    <a href="/privacy" className="hover:text-blue-600">Privacy Policy</a>
                    <a href="/terms" className="hover:text-blue-600">Terms of Service</a>
                </div>
            </div>
        </footer>
    )
}

export default Footer