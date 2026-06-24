# Vox

Vox is a lightweight, configurable chat plugin built for roleplay servers. It replaces Minecraft's default chat with customizable channels, character-based messaging, and network-wide private messages.

## Features

- Fully configurable chat channels
- Local and global chat with configurable ranges
- Channel aliases (such as `#shout`, `#whisper`, and `#ooc`)
- Character names instead of Minecraft usernames
- Roleplay chat formatting and emotes
- Cross-server private messaging with `/msg` and `/reply`
- Async chat handling for better performance

## Chat Channels

Channels are created through the config, making it easy to add or edit them without touching the code.

Each channel can define:
- Display name
- Color
- Chat radius (or global)
- Aliases
- Special flags (RP, OOC, Staff, etc.)

Players can also switch their active chat channel and temporarily override it by using channel aliases in their message.

## Roleplay Formatting

Vox includes simple formatting tools designed for roleplay.

- Character names are shown instead of Minecraft usernames.
- Supports channel prefixes and colors.
- Simple inline formatting:
  - `/text/` for italics
  - `^text^` for bold
- Supports emote-style messages while keeping formatting safe and consistent.

## Private Messages

Private messaging works across an entire Velocity network.

Commands:
- `/msg`
- `/reply`

Conversation history is remembered so replying is quick, even when players are on different servers.

## Planned Features

- Line-of-sight and wall muffling
- Channel priorities and overrides
- Per-player channel toggles
- Ignore and mute support
- Optional message logging

## Requirements

- Identities (for character data)
- Paper
- Velocity (for cross-server messaging)
