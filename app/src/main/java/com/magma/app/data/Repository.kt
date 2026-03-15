package com.magma.app.data

import com.magma.app.data.models.*

class Repository {
    private val colleges = mutableListOf<College>()

    fun seedDemo() {
        val clubA = Club(id = "c1", name = "Robotics Club", points = 120)
        val clubB = Club(id = "c2", name = "Drama Club", points = 80)
        val college = College(id = "col1", name = "Magma University", clubs = mutableListOf(clubA, clubB), points = 200)
        val user = User(id = "u1", name = "Alice", points = 50)
        college.members.add(user)
        colleges.clear()
        colleges.add(college)
        // seed feature collections
        posts.clear()
        posts.add(Post(id = "p1", authorId = "u1", title = "Welcome to Magma", body = "Community post"))

        items.clear()
        items.add(Item(id = "i1", sellerId = "u1", title = "Old Textbook", description = "Discrete Math", priceCents = 1500))

        apps.clear()
        apps.add(AppListing(id = "a1", developerId = "u1", title = "StudyBuddy", description = "Notes organizer"))

        videos.clear()
        videos.add(VideoContent(id = "v1", uploaderId = "u1", title = "Lecture 1", description = "Intro"))

        jobs.clear()
        jobs.add(Job(id = "j1", company = "Acme", role = "Intern", description = "Internship opening", stipendCents = 20000))

        events.clear()
        events.add(Event(id = "e1", title = "Hackathon", description = "24 hour hack", organizerId = "c1", timestamp = System.currentTimeMillis()))

        library.clear()
        library.add(LibraryResource(id = "lib1", title = "Algorithms", authors = listOf("CLRS")))

        mentorships.clear()
        mentorships.add(Mentorship(id = "m1", mentorId = "u1", menteeId = "u1", topic = "Kotlin"))
    }

    fun getColleges(): List<College> = colleges.toList()

    // Feature collections
    private val posts = mutableListOf<Post>()
    private val items = mutableListOf<Item>()
    private val apps = mutableListOf<AppListing>()
    private val videos = mutableListOf<VideoContent>()
    private val jobs = mutableListOf<Job>()
    private val events = mutableListOf<Event>()
    private val library = mutableListOf<LibraryResource>()
    private val mentorships = mutableListOf<Mentorship>()

    fun getPosts(): List<Post> = posts.toList()
    fun getItems(): List<Item> = items.toList()
    fun getApps(): List<AppListing> = apps.toList()
    fun getVideos(): List<VideoContent> = videos.toList()
    fun getJobs(): List<Job> = jobs.toList()
    fun getEvents(): List<Event> = events.toList()
    fun getLibrary(): List<LibraryResource> = library.toList()
    fun getMentorships(): List<Mentorship> = mentorships.toList()

    fun awardPointsToClub(collegeId: String, clubId: String, points: Int, reason: String) {
        val college = colleges.find { it.id == collegeId } ?: return
        val club = college.clubs.find { it.id == clubId } ?: return
        club.points += points
        college.points += points
        college.transactions.add(PointTransaction(reason = reason, amount = points))
    }
}
